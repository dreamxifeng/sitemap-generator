/*
 * Copyright (c) 2020-2030 Sishun.Co.Ltd. All Rights Reserved.
 */
package com.vevor.tools.seo.sitemap.service.impl;

import com.vevor.tools.seo.config.FileUploadConfiguration;
import com.vevor.tools.seo.sitemap.pojo.vo.SiteMapInfoVO;
import com.vevor.tools.seo.sitemap.rewritesourcecode.*;
import com.vevor.tools.seo.sitemap.service.SiteMapGeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.*;

/**
 * @Description ：生成siteMap.xml文件
 * @Program ：vevor-tools
 * @Author ：Li Hui
 * @Date ：Created in 2020/10/26 9:04
 * @Version ：1.0.1
 */
@Slf4j
@Service
public class SiteMapGeneratorServiceImpl implements SiteMapGeneratorService {

    /**
     * 产品类sitemap生成工具
     *
     * @param uploadConfigVo 配置参数
     * @param seoInfoConfig 对外开放接收参数
     * @return string 目录路径
     */
    @Override
    public List<File> generateSitemap(FileUploadConfiguration uploadConfigVo, SiteMapInfoVO seoInfoConfig) {
        //本地服务器文件生成路径
        //windows   is： user.home\AppData\Local\sitemap\
        //linux     is:    /tmp/sitemap
        List<File> articleFiles = null;
        File file = new File(System.getProperty("java.io.tmpdir")+"sitemap");
        if (!file.exists()) {
            if(file.mkdirs()){
                log.info("local Temporary File Created Successfully");
            }
        }
        log.info("sitemap generator start time"+new Date().toString());
        try {
            LinkedList<String> infoConfigImageUrl = seoInfoConfig.getImageUrl();
            String key = infoConfigImageUrl.get(0).split(",")[0];
            SitemapImageGenerator g1 = SitemapImageGenerator.builder(key, file)
                    .fileNamePrefix(seoInfoConfig.getPageTypeName())
                    .build();
            for (int i = 0; i <infoConfigImageUrl.size(); i++) {
                key = infoConfigImageUrl.get(i).split(",")[0];
                Map<String, Map<String,String>>map  = new HashMap<>(1);
                Map<String, String>map1=new HashMap<>(2);
                map1.put("hreflang",uploadConfigVo.getLanguage());
                map1.put("href",key);
                map.put(seoInfoConfig.getImageUrl().get(i),map1);
                GoogleImageSitemapUrl url =
                        new GoogleImageSitemapUrl.ImageOptions(seoInfoConfig.getImageUrl().get(i),map).build();
                g1.addUrl(url);
            }
            articleFiles = g1.write();
            log.info("sitemap generator stop time"+new Date().toString());
        } catch (MalformedURLException | URISyntaxException e) {
            e.printStackTrace();
        }
        return articleFiles;
    }

    /**
     * 非产品类sitemap生成工具
     *
     * @param uploadConfigVo 配置参数
     * @param seoInfoConfig 对外开放接收参数
     * @return string 目录路径
     */
    @Override
    public List<File> generateNoImageSitemap(FileUploadConfiguration uploadConfigVo, SiteMapInfoVO seoInfoConfig) {
        //本地服务器文件生成路径
        //windows   is： user.home\AppData\Local\sitemap\
        //linux     is:    /tmp/sitemap
        List<File> articleFiles = null;
        File file = new File(System.getProperty("java.io.tmpdir")+"sitemap");
        if (!file.exists()) {
            if(file.mkdirs()){
                log.info("local Temporary File Created Successfully");
            }
        }
        log.info("sitemap generator start time"+new Date().toString());
        try {
            SitemapNoImageGenerator g1 = SitemapNoImageGenerator
                    .builder(seoInfoConfig.getUrls().get(0), file)
                    .fileNamePrefix(seoInfoConfig.getPageTypeName())
                    .build();
            for (int i = 0; i <seoInfoConfig.getUrls().size(); i++) {
                Map<String, Map<String,String>>map  = new HashMap<>(1);
                Map<String, String>map1=new HashMap<>(2);
                map1.put("hreflang",uploadConfigVo.getLanguage());
                map1.put("href",seoInfoConfig.getUrls().get(i));
                map.put(seoInfoConfig.getUrls().get(i),map1);
                GoogleNoImageSitemapUrl url =
                        new GoogleNoImageSitemapUrl.ImageOptions(seoInfoConfig.getUrls().get(i),map).build();
                g1.addUrl(url);
            }
            // 生成 sitemap 文件
            articleFiles = g1.write();
            log.info("sitemap generator stop time"+new Date().toString());
        } catch (MalformedURLException | URISyntaxException e) {
            e.printStackTrace();
        }
        return articleFiles;
    }

    /**
     * 生成sitemap_index.xml文件
     *
     * @param uploadConfigVo 参数对象
     * @param linkedList url集合
     * @return string 目录路径
     */
    public String indexMapGenerator(FileUploadConfiguration uploadConfigVo, List<File> articleFiles,
                                    LinkedList<String> linkedList, SiteMapInfoVO siteMapInfoVO) throws MalformedURLException {
        // 生成 sitemap 文件
        List<String> fileNames = new ArrayList<>();
        articleFiles.forEach(e -> fileNames.add(e.getName()));
        // 构造 sitemap_index 生成器
        W3CDateFormat dateFormat = new W3CDateFormat(W3CDateFormat.Pattern.DAY);
        SitemapIndexGenerator sitemapIndexGenerator = new SitemapIndexGenerator
                .Options(linkedList.get(0), new File(System.getProperty("java.io.tmpdir")+"sitemap" +"/"+
                "sitemap_index.xml"))
                .allowEmptyIndex(true)
                .dateFormat(dateFormat)
                .autoValidate(true)
                .build();
        fileNames.forEach(e -> {
            try {
                // 组装 sitemap 文件 URL 地址
                // TODO 生成索引文件需要统一的域名以及符合所有url的域名保持一致
                sitemapIndexGenerator.addUrl(siteMapInfoVO.getDomain()+"/" +uploadConfigVo.getSiteMapFilePath() +
                        "\\"+ e);
            } catch (MalformedURLException e1) {
                log.info("完成");
            }
        });
        // 生成 sitemap_index 文件
        sitemapIndexGenerator.write();
        return System.getProperty("java.io.tmpdir")+"sitemap" +"/"+
                "sitemap_index.xml";
    }
}
