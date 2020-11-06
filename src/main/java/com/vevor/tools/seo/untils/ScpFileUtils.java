/*
 * Copyright (c) 2020-2030 Sishun.Co.Ltd. All Rights Reserved.
 */
package com.vevor.tools.seo.untils;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.vevor.tools.seo.config.FileUploadConfiguration;
import com.vevor.tools.seo.sitemap.pojo.vo.SiteMapInfoVO;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @Description ：Scp文件处理工具(上传,解压,压缩)
 * @Program ：vevor-tools
 * @Author ：Li Hui
 * @Date ：Created in 2020/11/3 16:00
 * @Version ：1.0.1
 */
@Slf4j
public class ScpFileUtils {

    /**
     * scp命令上传robot文件
     *
     * @param command 执行命令行
     * @throws IOException 异常
     * @throws InterruptedException 异常
     */
    public void scpUploadFile(String command) throws IOException,
            InterruptedException {
        Process process = Runtime.getRuntime().exec(command);
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
     * @param command 执行命令行
     * @throws JSchException 异常
     */
    public void unzip(FileUploadConfiguration uploadConfigVO, String command, SiteMapInfoVO siteMapInfoVO) throws  JSchException {
        JSch jSch = new JSch();
        String sshKeyPath = System.getProperty("user.home") + "/.ssh/id_rsa";
        jSch.addIdentity(sshKeyPath);
        Session session = jSch.getSession(uploadConfigVO.getUsername(), siteMapInfoVO.getIp(), uploadConfigVO.getPort());
        session.setConfig("StrictHostKeyChecking","no");
        session.connect();
        ChannelExec exec = (ChannelExec) session.openChannel("exec");
        exec.setCommand(command.getBytes(
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
     * @param zipName 压缩文件名称
     * @return zipFile  压缩包路径:与文件路径相同
     */
    public File zipFiles(List<File> srcFiles,String zipName) {
        File zipFile = new File(srcFiles.get(0).getParent()+"/"+zipName);
        if (!zipFile.exists()) {
            try {
                zipFile.createNewFile();
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
