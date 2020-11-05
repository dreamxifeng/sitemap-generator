/*
 * Copyright (c) 2020-2030 Sishun.Co.Ltd. All Rights Reserved.
 */
package com.vevor.tools.seo.sitemap.service.impl;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.vevor.tools.seo.config.FileUploadConfiguration;
import com.vevor.tools.seo.sitemap.pojo.vo.SiteMapInfoVO;
import com.vevor.tools.seo.sitemap.service.SiteMapFtpUploadService;
import com.vevor.tools.seo.untils.FtpFileUtils;
import com.vevor.tools.seo.untils.LoginSftpServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description ：SiteMap.XML ftp方式文件上传
 * @Program ：vevor-tools-seo
 * @Author ：Li Hui
 * @Date ：Created in 2020/10/26 11:56
 * @Version ：1.0.1
 */
@Slf4j
@Service
public class SiteMapFtpUploadServiceImpl implements SiteMapFtpUploadService {

    @Autowired
    private SiteMapGeneratorServiceImpl siteMapGeneratorService;

    @Autowired
    private FileUploadConfiguration fileUploadConfiguration;
    /**
     *产品类sitemap生成
     *
     * @param siteMapInfoVO url集合
     * @return boolean 上传结果
     */
    @Override
    public List<String> siteMapUpload(SiteMapInfoVO siteMapInfoVO) {
        //生成xml
        List<File> sitemapFiles = siteMapGeneratorService.generateSitemap(fileUploadConfiguration,siteMapInfoVO);
        //是否生成sitemap_index.xml文件
        String s=null;
        if (fileUploadConfiguration.getIsIndexGenerator()){
            //判断索引文件是否生成生成成功
            try {
                siteMapInfoVO.getUrls().addAll(siteMapInfoVO.getImageUrl().size(),siteMapInfoVO.getImageUrl());
                s = siteMapGeneratorService.indexMapGenerator(fileUploadConfiguration, sitemapFiles,
                        siteMapInfoVO.getImageUrl(),siteMapInfoVO);
                log.info(s);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }else{
            s= sitemapFiles.get(0).getName();
            log.info(s);
        }
        LoginSftpServer loginSftpServer = new LoginSftpServer();
        FtpFileUtils ftpFileUtils = new FtpFileUtils();
        //创建连接
        ChannelSftp sftp = loginSftpServer.login(fileUploadConfiguration.getUsername(), fileUploadConfiguration.getIp(),
                fileUploadConfiguration.getPort(), fileUploadConfiguration.getPrivateKey());
        //生成路径
        ftpFileUtils.createDir(fileUploadConfiguration.getSiteMapFilePath(),sftp);

        //压缩xml文件 上传至服务器
        File zipFiles = ftpFileUtils.zipFiles(sitemapFiles);
        ftpFileUtils.upload(fileUploadConfiguration.getSiteMapFilePath(),zipFiles.getPath(),sftp);
        log.info("the Compressed FileIs Uploaded To TheServer");
        //删除远程服务器压缩文件
        try {
            try {
                ftpFileUtils.unzip(fileUploadConfiguration,zipFiles.getName(),sitemapFiles);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (JSchException e) {
            e.printStackTrace();
        }
        log.info("remove Remote Server Zip");
        boolean returnExist = ftpFileUtils.isExist(
                fileUploadConfiguration.getSiteMapFilePath() + "/" + s, sftp);
        log.info("Remote server file is"+returnExist);
        //关闭连接
        loginSftpServer.logout();
        //删除本地文件
        for (File sitemapFile : sitemapFiles) {
            try {
                FileUtils.deleteQuietly (sitemapFile);
                log.info("localhost file delete success ");
            } catch (Exception e) {
                log.info("localhost file delete fail ");
                e.printStackTrace();
            }
        }
        return getStrings(fileUploadConfiguration, sitemapFiles, s, returnExist,zipFiles);
    }

    /**
     *非产品类sitemap生成
     *
     * @param siteMapInfoVO url集合
     * @return boolean 上传结果
     */
    @Override
    public List<String> siteMapNoImageUpload(SiteMapInfoVO siteMapInfoVO) {
        //生成xml 返回绝对路径
        List<File> sitemapFiles = siteMapGeneratorService.generateNoImageSitemap(fileUploadConfiguration,siteMapInfoVO);
        //是否生成sitemap_index.xml文件
        String s="";
        if (fileUploadConfiguration.getIsIndexGenerator()){
            //判断索引文件是否生成生成成功
            try {
                siteMapInfoVO.getUrls().addAll(siteMapInfoVO.getImageUrl().size(),siteMapInfoVO.getImageUrl());
                s = siteMapGeneratorService.indexMapGenerator(fileUploadConfiguration,
                        sitemapFiles,
                        siteMapInfoVO.getUrls(),
                        siteMapInfoVO);
                log.info(s);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }else{
            s= sitemapFiles.get(0).getName();
            log.info(s);
        }
        LoginSftpServer loginSftpServer = new LoginSftpServer();
        FtpFileUtils ftpFileUtils = new FtpFileUtils();
        //创建连接
        ChannelSftp sftp = loginSftpServer.login(fileUploadConfiguration.getUsername(), fileUploadConfiguration.getIp(),
                fileUploadConfiguration.getPort(), fileUploadConfiguration.getPrivateKey());
        //生成路径
        ftpFileUtils.createDir(fileUploadConfiguration.getSiteMapFilePath(),sftp);

        //压缩xml文件 上传至服务器
        File zipFiles = ftpFileUtils.zipFiles(sitemapFiles);
        ftpFileUtils.upload(fileUploadConfiguration.getSiteMapFilePath(),zipFiles.getPath(),sftp);
        log.info("the Compressed File Is Uploaded To TheServer");
        //删除远程服务器压缩文件
        try {
            try {
                ftpFileUtils.unzip(fileUploadConfiguration,zipFiles.getName(),sitemapFiles);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (JSchException e) {
            e.printStackTrace();
        }
        log.info("remove Remote Server Zip");
        boolean returnExist = ftpFileUtils.isExist(
                fileUploadConfiguration.getSiteMapFilePath() + "/" + s, sftp);
        log.info("Remote server file is"+returnExist);
        //关闭连接
        loginSftpServer.logout();
        return getStrings(fileUploadConfiguration, sitemapFiles, s, returnExist,zipFiles);
    }

    private List<String> getStrings(FileUploadConfiguration fileUploadConfiguration,
                                    List<File> sitemapFiles, String s,
                                    boolean returnExist,File zipFiles) {
        List<String> list = new ArrayList<>();
        if (returnExist && !fileUploadConfiguration.getIsIndexGenerator()){
            list.add( sitemapFiles.iterator().next().getName()) ;
            deleteFiles(sitemapFiles,zipFiles);
            return list;
        }
        if (returnExist && fileUploadConfiguration.getIsIndexGenerator()){
            deleteFiles(sitemapFiles,zipFiles);
            list.add(s);
            return list;
        }
        deleteFiles(sitemapFiles,zipFiles);
        return null;
    }

    public void deleteFiles(List<File> sitemapFiles,File zipFile){
        //删除本地临时目录下生成的临时文件
        for (File sitemapFile : sitemapFiles) {
            try {
                FileUtils.deleteQuietly (sitemapFile);
            } catch (Exception e) {
                log.info("localhost file delete fail ");
                e.printStackTrace();
            }
        }
        FileUtils.deleteQuietly(zipFile);
        log.info("localhost file delete success ");
    }
}
