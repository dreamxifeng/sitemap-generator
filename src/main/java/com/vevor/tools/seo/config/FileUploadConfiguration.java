/*
 * Copyright (c) 2020-2030 Sishun.Co.Ltd. All Rights Reserved.
 */
package com.vevor.tools.seo.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Description ：文件上传所需参数配置类
 * @Program ：vevor-tools
 * @Author ：Li Hui
 * @Date ：Created in 2020/10/26 13:21
 * @Version ：1.0.1
 */
@Configuration
@ConfigurationProperties(prefix = "sitemap")
@Data
@ToString
public class FileUploadConfiguration {

    /**
     * 服务器地址
     */
    private String ip;

    /**
     * 服务器地址开放端口
     */
    private  Integer port;

    /**
     * 服务器用户
     */
    private String username;

    /**
     * 服务器用户密码
     */
    private String password;

    /**
     *本地服务器文件生成路径：暂不用
     *windows   is： user.home\AppData\Local\sitemap\
     *linux     is:    /tmp/sitemap
     */
    private String destinationPath ;

    /**
     * 本地私钥地址
     */
    private String privateKey ;

    /**
     * 是否生成索引文件
     */
    private Boolean isIndexGenerator;

    /**
     * 本地公钥地址
     */
    private String passphrase ;

    /**
     *远程服务器保存文件名
     */
    private String robotFileName ;

    /**
     *sitemap.xml文件存放 远程Nginx服务器文件目录
     */
    private String siteMapFilePath;

    /**
     *robot.txt文件存放 远程Nginx服务器文件目录
     */
    private String robotFilePath;

    /**
     * siteMap域名地址所属语种
     */
    private String language;

}
