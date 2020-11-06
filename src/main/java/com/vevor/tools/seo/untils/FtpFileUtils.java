/*
 * Copyright (c) 2020-2030 Sishun.Co.Ltd. All Rights Reserved.
 */
package com.vevor.tools.seo.untils;

import com.jcraft.jsch.*;
import com.vevor.tools.seo.config.FileUploadConfiguration;
import com.vevor.tools.seo.sitemap.pojo.vo.SiteMapInfoVO;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @Description ：服务器基于ftp连接方式的文件读写工具类
 * @Program ：vevor-tools
 * @Author ：Li Hui
 * @Date ：Created in 2020/10/21 16:20
 * @Version ：1.0.1
 */
@Slf4j
public class FtpFileUtils {

    /**
     * 上传单个文件
     *
     * @param directory     上传到SFTP服务器的路径
     * @param uploadFileUrl 文件路径
     */
    public void upload(String directory, String uploadFileUrl,ChannelSftp sftp){
        File file = new File(uploadFileUrl);
        try{
            upload(directory, file.getName(), new FileInputStream(file),sftp);
        }catch (FileNotFoundException | SftpException e){
            log.error("abnormal Upload File ！", e);
        }
    }

    /**
     * 将输入流上传到SFTP服务器，作为文件
     *
     * @param directory     上传到SFTP服务器的路径
     * @param sftpFileName  上传到SFTP服务器后的文件名
     * @param input         输入流
     * @throws SftpException 异常
     */
    public void upload(String directory, String sftpFileName, InputStream input,ChannelSftp sftp) throws SftpException {
        long start = System.currentTimeMillis();
        try{
            //如果文件夹不存在，则创建文件夹
            if(sftp.ls(directory) == null){
                sftp.mkdir(directory);
            }
            //切换到指定文件夹
            sftp.cd(directory);
        }catch (SftpException e){
            //创建不存在的文件夹，并切换到文件夹
            sftp.mkdir(directory);
            sftp.cd(directory);
        }
        //
        sftp.put(input, sftpFileName,2);
        log.info("file Uploaded Successfully！ 耗时：{}ms",(System.currentTimeMillis() - start));
    }

    /**
     * 将字符串按照指定编码格式上传到SFTP服务器
     *
     * @param directory       上传到SFTP服务器的路径
     * @param sftpFileName    上传SFTP服务器后的文件名
     * @param dataStr         字符串
     * @param charsetName     字符串的编码格式
     */
    public void upload(String directory, String sftpFileName, String dataStr, String charsetName,ChannelSftp sftp){
        try{
            upload(directory, sftpFileName, new ByteArrayInputStream(dataStr.getBytes(charsetName)),sftp);
        }catch (UnsupportedEncodingException | SftpException e){
            log.error("abnormal Upload File ", e);
        }
    }

    /**
     * 删除文件
     *
     * @param directory         SFTP服务器的文件路径
     * @param deleteFileName    删除的文件名称
     */
    public void delete(String directory, String deleteFileName,ChannelSftp sftp){
        try{
            sftp.cd(directory);
            sftp.rm(deleteFileName);
        }catch (SftpException e){
            log.error("file Deletion Exception！", e);
        }
    }

    /**
     * 创建一个文件目录
     *
     * @param createPath        路径
     */
    public void createDir(String createPath, ChannelSftp sftp) {
        try {
            if (isDirExist(createPath,sftp)) {
                sftp.cd(createPath);
                return;
            }
            String[] pathAiry = createPath.split("/");
            StringBuilder filePath = new StringBuilder("/");
            for (String path : pathAiry) {
                if ("".equals(path)) {
                    continue;
                }
                filePath.append(path).append("/");
                if (isDirExist(filePath.toString(),sftp)) {
                    sftp.cd(filePath.toString());
                } else {
                    // 建立目录
                    sftp.mkdir(filePath.toString());
                    // 进入并设置为当前目录
                    sftp.cd(filePath.toString());
                }
            }
            sftp.cd(createPath);
        } catch (SftpException e) {
            log.error("directory Creation Exception！", e);
        }
    }

    /**
     * 判断目录是否存在
     * @param directory     路径
     * @return boolean
     */
    public boolean isDirExist(String directory,ChannelSftp sftp) {
        boolean isDirExistFlag = false;
        try {
            SftpATTRS lstat = sftp.lstat(directory);
            isDirExistFlag = true;
            return lstat.isDir();
        } catch (Exception e) {
            if ("no such file".equals(e.getMessage().toLowerCase())) {
                isDirExistFlag = false;
            }
        }
        return isDirExistFlag;
    }

    /**
     * 判断文件或目录是否存在
     * @param path     路径
     * @return boolean
     */
    public boolean isExist(String path, ChannelSftp sftp){
        boolean isExist=true;
        try {
            sftp.lstat(path);
            isExist = true;
        } catch (Exception e) {
            if ("no such file".equals(e.getMessage().toLowerCase())) {
                isExist = false;
            }
        }
        return isExist;
    }

    /**
     * 解压服务器文件文件
     *
     * @param uploadConfigVO 配置参数
     * @throws JSchException 异常
     */
    public void unzip(FileUploadConfiguration uploadConfigVO, String fileName, List<File> sitemapFiles,
                      SiteMapInfoVO siteMapInfoVO) throws JSchException,
            IOException {
        JSch jSch = new JSch();
        List<String> list = new ArrayList<>();
        list.add( sitemapFiles.iterator().next().getName()) ;
        String sshKeyPath = System.getProperty("user.home") + "/.ssh/id_rsa";
        jSch.addIdentity(sshKeyPath);
        Session session = jSch.getSession(uploadConfigVO.getUsername(), siteMapInfoVO.getIp(), uploadConfigVO.getPort());
        session.setConfig("StrictHostKeyChecking","no");
        session.setTimeout(1000);
        session.connect();
        ChannelExec exec = (ChannelExec) session.openChannel("exec");
        StringBuilder string =new StringBuilder();
        for (String str: list) {
            string.append("rm -f ").append(str).append("\n");
        }
        //1切换到压缩包目录里，2.删除压缩包内与服务器相同的文件，3.解压压缩包，4删除压缩包
        String s = "cd " + uploadConfigVO.getSiteMapFilePath() + "\n"
                + string
                +"y"+"\n"
                + "unzip " + fileName + "\n"
                + "rm -f " + fileName+"\n";
        //1切换到压缩包目录里，2解压压缩包，3 Y-> 确认解压并覆盖掉存在相同的文件，4删除压缩包
        exec.setCommand(s.getBytes(
                StandardCharsets.UTF_8
        ));
        log.info("shell run success"+"\n"+s);
        exec.connect();
        // get stdout
        InputStream in = exec.getInputStream();
        byte[] tmp = new byte[1024];
        while (true) {
            while (in.available() > 0) {
                int i = in.read(tmp, 0, 1024);
                if (i < 0){
                    break;
                }
                System.out.print(new String(tmp, 0, i));
            }
            if (exec.isClosed()) {
                System.out.println("exit-status: " + exec.getExitStatus());
                break;
            }
            try {
                Thread.sleep(10);
            } catch (Exception ee) {
                log.info("error");
            }
        }
        log.info("Unzip the server file success");
        exec.disconnect();
        log.info("exec is close");
        session.disconnect();
        log.info("session is close");
    }
    /**
     * 多个文件压缩为一个压缩包
     *
     * @param srcFiles file文件集合
     */
    public File zipFiles(List<File> srcFiles) {
        // 判断压缩后的文件存在不，不存在则创建
        File zipFile = new File(srcFiles.get(0).getParent()+"/"+"sitemap.zip");
        if (!zipFile.exists()) {
            try {
                if (zipFile.createNewFile()){
                    log.info("zip File created successfully");
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

