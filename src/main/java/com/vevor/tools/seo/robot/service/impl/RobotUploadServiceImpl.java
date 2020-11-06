/*
 * Copyright (c) 2020-2030 Sishun.Co.Ltd. All Rights Reserved.
 */
package com.vevor.tools.seo.robot.service.impl;

import com.jcraft.jsch.*;
import com.vevor.tools.seo.config.FileUploadConfiguration;
import com.vevor.tools.seo.robot.pojo.vo.RobotInfoVO;
import com.vevor.tools.seo.robot.service.RobotUploadService;
import com.vevor.tools.seo.untils.FtpFileUtils;
import com.vevor.tools.seo.untils.LoginSftpServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @Description ：服务器Robots.txt文件上传
 * @Program ：vevor-tools-seo
 * @Author ：Li Hui
 * @Date ：Created in 2020/10/26 11:56
 * @Version ：1.0.1
 */
@Slf4j
@Service
public class RobotUploadServiceImpl implements RobotUploadService {

    @Autowired
    private FileUploadConfiguration fileUploadConfiguration;

    /**
     *以String字符的方式上传robot内容
     *
     * @param robotInfoVO 上传文件所需参数对象->存储内容
     * @return boolean 上传结果
     */
    @Override
    public boolean uploadRobot(RobotInfoVO robotInfoVO) {
        LoginSftpServer loginSftpServer = new LoginSftpServer();
        FtpFileUtils ftpFileUtils = new FtpFileUtils();
        //创建连接
        ChannelSftp sftp = loginSftpServer.login(fileUploadConfiguration.getUsername(), robotInfoVO.getIp(),
                fileUploadConfiguration.getPort(), fileUploadConfiguration.getPrivateKey());
        //判断文件是否存在 存在则删除
        boolean exist = ftpFileUtils.isExist(
                fileUploadConfiguration.getRobotFilePath() + "/" + fileUploadConfiguration.getRobotFileName(), sftp);
        if (exist){
            ftpFileUtils.delete(fileUploadConfiguration.getRobotFilePath(), fileUploadConfiguration.getRobotFileName(),sftp);
        }
        //以String字符为数据的流方式上传文件
        ftpFileUtils.upload(fileUploadConfiguration.getRobotFilePath(), fileUploadConfiguration.getRobotFileName(),
                robotInfoVO.getContent(),
                "UTF-8",sftp);
        //判断文件是否生成生成成功
        boolean returnExist = ftpFileUtils.isExist(
                fileUploadConfiguration.getRobotFilePath() + "/" + fileUploadConfiguration.getRobotFileName(), sftp);
        //关闭连接
        loginSftpServer.logout();
        return returnExist;
    }

    @Override
    public boolean uploadRobotByScp(RobotInfoVO robotInfoVO) {
        File file = new File(System.getProperty("java.io.tmpdir")+"/robot"+"/robot.txt");
        if (!file.exists()) {
            try {
                if(file.createNewFile()){
                    log.info("File created successfully");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileWriter fileWriter = new FileWriter(System.getProperty("java.io.tmpdir")+"/robot"+"/robot.txt");
            fileWriter.write(robotInfoVO.getContent());
            fileWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<File> files =new ArrayList<>();
        files.add(file);
        //压缩
        File zipFiles = zipFiles(files);
        //上传
        try {
            scpUploadFile(zipFiles.getPath(),fileUploadConfiguration,robotInfoVO);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        List<String> list = new ArrayList<>();
        list.add(files.iterator().next().getName());
        // 解压
        try {
            unzip(fileUploadConfiguration,zipFiles.getName(),list,robotInfoVO);
        } catch (JSchException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * scp命令上传robot文件
     *
     * @param path 文件路径
     * @throws IOException 异常
     * @throws InterruptedException 异常
     */
    public void scpUploadFile(String path, FileUploadConfiguration uploadConfigVO,RobotInfoVO robotInfoVO) throws IOException,
            InterruptedException {
        Process process = Runtime.getRuntime().exec("scp -r "+path
                + " "+uploadConfigVO.getUsername()+"@"+robotInfoVO.getIp()+":/"+uploadConfigVO.getRobotFilePath());
        InputStream errorStream = process.getErrorStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(errorStream));
        String line;
        StringBuilder errorMessage = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {
            errorMessage.append(line);
        }
        int i = process.waitFor();
        if (i != 0) {
            throw new RuntimeException("命令执行失败， 返回值："+ i + "\r\n失败原因：" + errorMessage.toString());
        }
        log.info("The SCP command uploads the robot file success");
    }

    /**
     * 解压服务器文件文件
     *
     * @param uploadConfigVO 配置参数
     * @throws JSchException 异常
     */
    public void unzip(FileUploadConfiguration uploadConfigVO,String fileName,List<String> list,RobotInfoVO robotInfoVO) throws  JSchException {
        JSch jSch = new JSch();
        String sshKeyPath = System.getProperty("user.home") + "/.ssh/id_rsa";
        jSch.addIdentity(sshKeyPath);
        Session session = jSch.getSession(uploadConfigVO.getUsername(), robotInfoVO.getIp(), uploadConfigVO.getPort());
        session.setConfig("StrictHostKeyChecking","no");
        session.connect();
        ChannelExec exec = (ChannelExec) session.openChannel("exec");
        StringBuilder string =new StringBuilder();
        for (String str: list) {
            string.append("rm -f ").append(str).append("\n");
        }
        //1切换到压缩包目录里，2.删除压缩包内与服务器相同的文件，3.解压压缩包，4删除压缩包
        //先解压文件，如果本身就有压缩包内相同名称的文件，
        // linux会让输入一次y/n,多次拼接回车与y,时不时会发生程序卡住,由于是远程，也不清楚远程那边执行是什么情况。
        //时间有限就没去远程测试，于是就提前执行删除命令，有或没有，都不会有二次手动输入。后来分析流程也是合理的.
        String s = "cd " + uploadConfigVO.getRobotFilePath() + "\n"
                + string
                + "unzip " + fileName +"\n"
                + "rm -f " + fileName +"\n";
        exec.setCommand(s.getBytes(
                StandardCharsets.UTF_8
        ));
        exec.connect();
        InputStream in ;
        try {
            in = exec.getInputStream();
            byte[] tmp = new byte[1024];
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0) {
                        break;
                    }
                    System.out.print(new String(tmp, 0, i));
                }
                if (exec.isClosed()) {
                    log.info("exit-status: " + exec.getExitStatus());
                    break;
                }
                try {
                    Thread.sleep(10);
                } catch (Exception ee) {
                    log.info("error");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("Unzip the server file success");
        exec.disconnect();
        session.disconnect();
    }

    /**
     * 多个文件压缩为一个压缩包
     *
     * @param srcFiles file文件集合
     * @return s  压缩包路径
     */
    public File zipFiles(List<File> srcFiles) {
        File zipFile = new File(srcFiles.get(0).getParent()+"/"+"robot.zip");
        if (!zipFile.exists()) {
            try {
                if (zipFile.createNewFile()){
                    log.info("compressed Package Created Successfully");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileOutputStream fileOutputStream ;
        ZipOutputStream zipOutputStream ;
        FileInputStream fileInputStream = null;
        try {
            fileOutputStream = new FileOutputStream(zipFile);
            zipOutputStream = new ZipOutputStream(fileOutputStream);
            ZipEntry zipEntry ;
            for (File srcFile : srcFiles) {
                // 将源文件数组中的当前文件读入 FileInputStream 流中
                fileInputStream = new FileInputStream(srcFile);
                // 实例化 ZipEntry 对象，源文件数组中的当前文件
                zipEntry = new ZipEntry(srcFile.getName());
                zipOutputStream.putNextEntry(zipEntry);
                // 该变量记录每次真正读的字节个数
                int len;
                // 定义每次读取的字节数组
                byte[] buffer = new byte[1024];
                while ((len = fileInputStream.read(buffer)) > 0) {
                    zipOutputStream.write(buffer, 0, len);
                }
            }
            zipOutputStream.closeEntry();
            zipOutputStream.close();
            if (fileInputStream!=null){
                fileInputStream.close();
            }
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("zip localhost file success");
        return zipFile;
    }

}
