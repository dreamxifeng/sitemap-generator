/*
 * Copyright (c) 2020-2030 Sishun.Co.Ltd. All Rights Reserved.
 */
package com.vevor.tools.seo.sitemap.service.impl;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.vevor.tools.seo.config.FileUploadConfiguration;
import com.vevor.tools.seo.sitemap.pojo.vo.SiteMapInfoVO;
import com.vevor.tools.seo.sitemap.service.SiteMapScpUploadService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author ：Li Hui
 * @version ：1.0.1
 * @description ：sitemap压缩包scp方式上传服务器及解压
 * @program ：vevor-tools
 * @date ：Created in 2020/11/3 16:08
 */
@Slf4j
@Service
public class ScpSiteMapZipImpl implements SiteMapScpUploadService {

    @Autowired
    private SiteMapGeneratorServiceImpl siteMapGeneratorService;

    @Autowired
    private FileUploadConfiguration uploadConfigVo;

    /**
     * 生成多个sitemap.xml并压缩为压缩包
     *
     * @param seoInfoConfig 参数对象
     */
    @Override
    public List<String> siteMapUpload( SiteMapInfoVO seoInfoConfig) {
        //生成xml 返回绝对路径
        List<File> sitemapFiles = siteMapGeneratorService.generateSitemap(uploadConfigVo, seoInfoConfig);
        //是否生成sitemap_index.xml文件
        if (uploadConfigVo.getIsIndexGenerator()) {
            try {
                String s ;
                seoInfoConfig.getUrls().addAll(seoInfoConfig.getImageUrl().size(),seoInfoConfig.getImageUrl());
                s = siteMapGeneratorService.indexMapGenerator(uploadConfigVo, sitemapFiles, seoInfoConfig.getUrls(),seoInfoConfig);
                log.info("index file path is : "+s);
                sitemapFiles.add(new File(s));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return  getList(sitemapFiles,uploadConfigVo,seoInfoConfig);
    }

    /**
     * 生成多个sitemap.xml并压缩为压缩包
     *
     * @param seoInfoConfig 参数对象
     */
    @Override
    public List<String> siteMapNoImageUpload( SiteMapInfoVO seoInfoConfig) {
        //生成xml 返回绝对路径
        List<File> sitemapFiles = siteMapGeneratorService.generateNoImageSitemap(uploadConfigVo, seoInfoConfig);
        //是否生成sitemap_index.xml文件
        String s ;
        if (uploadConfigVo.getIsIndexGenerator()) {
            try {
                seoInfoConfig.getUrls().addAll(seoInfoConfig.getImageUrl().size(),seoInfoConfig.getImageUrl());
                s = siteMapGeneratorService.indexMapGenerator(uploadConfigVo, sitemapFiles, seoInfoConfig.getUrls(),seoInfoConfig);
                log.info("index file path is : "+s);
                sitemapFiles.add(new File(s));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return  getList(sitemapFiles,uploadConfigVo,seoInfoConfig);
    }

    private List<String> getList(List<File> sitemapFiles,
                                 FileUploadConfiguration uploadConfigVo,SiteMapInfoVO siteMapInfoVO){
        //压缩文件
        File zipPath = zipFiles(sitemapFiles);
        List<String> list = new ArrayList<>();
        list.add(sitemapFiles.iterator().next().getName());
        log.info("compressed File Path:"+zipPath.getPath());
        //上传文件
        try {
            siteMapScpUpload(zipPath.getPath(),uploadConfigVo,siteMapInfoVO);
            log.info("file Uploaded Successfully");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        log.info("The compressed package was uploaded to the server successfully");
        //解压文件
        try {
            unzip(uploadConfigVo,zipPath.getName(),list,siteMapInfoVO);
        } catch (JSchException e) {
            e.printStackTrace();
        }
        log.info("The compressed package was decompressed successfully,redundant Files Have Been Deleted");
        deleteFiles(sitemapFiles);
        FileUtils.deleteQuietly(zipPath);
        log.info(zipPath.getName()+"the Local Package Has Been Deleted");
        return list;
    }

    /**
     * scp命令上传文件
     *
     * @param path 文件路径
     * @param fileUploadConfiguration 配置参数
     * @throws IOException 异常
     * @throws InterruptedException 异常
     */
    public void siteMapScpUpload(String path,
                                 FileUploadConfiguration fileUploadConfiguration,SiteMapInfoVO siteMapInfoVO) throws IOException,
            InterruptedException {
        Process process = Runtime.getRuntime().exec("scp -r "+path
                + " "+fileUploadConfiguration.getUsername()+"@"+siteMapInfoVO.getIp()+":"+ fileUploadConfiguration.getSiteMapFilePath());
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
    }

    /**
     * 解压服务器文件文件
     *
     * @param uploadConfigVO 配置参数
     * @throws JSchException 异常
     */
    public void unzip(FileUploadConfiguration uploadConfigVO, String fileName,List<String>list,SiteMapInfoVO siteMapInfoVO) throws JSchException {
        JSch jSch = new JSch();
        String sshKeyPath = System.getProperty("user.home") + "/.ssh/id_rsa";
        jSch.addIdentity(sshKeyPath);
        Session session = jSch.getSession(uploadConfigVO.getUsername(), siteMapInfoVO.getIp(), uploadConfigVO.getPort());
        session.setConfig("StrictHostKeyChecking","no");
        session.connect();
        ChannelExec exec = (ChannelExec) session.openChannel("exec");
        StringBuilder string =new StringBuilder();
        for (String str: list) {
            string.append("rm -f ").append(str).append("\n");
        }
        //1切换到压缩包目录里，2.删除压缩包内与服务器相同的文件，3.解压压缩包，4删除压缩包
        String s = "cd " + uploadConfigVO.getSiteMapFilePath() + "\n"
                + string
                + "unzip " + fileName + "\n"
                + "rm -f " + fileName+"\n";
        exec.setCommand(s.getBytes(
                StandardCharsets.UTF_8
        ));
        exec.connect();
        InputStream in;
        try {
            in = exec.getInputStream();
            byte[] tmp = new byte[1024];
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0){
                        break;
                    }
                    log.info(new String(tmp, 0, i));
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
        // 判断压缩后的文件存在不，不存在则创建
        File zipFile = new File(srcFiles.get(0).getParent() + "/" + "sitemap.zip");
        if (!zipFile.exists()) {
            try {
                if (zipFile.createNewFile()){
                    log.info("zip File created successfully");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 创建 FileOutputStream 对象
        FileOutputStream fileOutputStream;
        // 创建 ZipOutputStream
        ZipOutputStream zipOutputStream;
        // 创建 FileInputStream 对象
        FileInputStream fileInputStream = null;
        try {
            // 实例化 FileOutputStream 对象
            fileOutputStream = new FileOutputStream(zipFile);
            // 实例化 ZipOutputStream 对象
            zipOutputStream = new ZipOutputStream(fileOutputStream);
            // 创建 ZipEntry 对象
            ZipEntry zipEntry;
            // 遍历源文件数组
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
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return zipFile;
    }

    /**
     *删除本地临时目录下生成的临时文件
     *
     * @param sitemapFiles 本地文件
     */
    public void deleteFiles(List<File> sitemapFiles){
        //删除本地临时目录下生成的临时文件
        for (File sitemapFile : sitemapFiles) {
            try {
                FileUtils.deleteQuietly (sitemapFile);
            } catch (Exception e) {
                log.info("localhost file delete fail ");
                e.printStackTrace();
            }
        }
    }

}
