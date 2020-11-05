/*
 * Copyright (c) 2020-2030 Sishun.Co.Ltd. All Rights Reserved.
 */
package com.vevor.tools.seo.sitemap.service;

import com.vevor.tools.seo.config.FileUploadConfiguration;
import com.vevor.tools.seo.sitemap.pojo.vo.SiteMapInfoVO;

import java.io.File;
import java.util.List;

/**
 * @Description ：生成SiteMap.xml文件
 * @Program ：vevor-tools-seo
 * @Author ：Li Hui
 * @Date ：Created in 2020/10/26 11:56
 * @Version ：1.0.1
 */
public interface SiteMapGeneratorService {

    /**
     * 非产品类sitemap
     * @param fileUploadConfiguration 全局配置
     * @param siteMapInfoVO 对外开放所需参数
     * @return 生成的文件集合
     */
     List<File> generateSitemap(FileUploadConfiguration fileUploadConfiguration, SiteMapInfoVO siteMapInfoVO);

    /**
     * 产品类sitemap
     * @param fileUploadConfiguration 全局配置
     * @param siteMapInfoVO 对外开放所需参数
     * @return 生成的文件集合
     */
     List<File> generateNoImageSitemap(FileUploadConfiguration fileUploadConfiguration, SiteMapInfoVO siteMapInfoVO);

}
