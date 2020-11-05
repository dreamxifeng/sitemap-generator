/*
 * Copyright (c) 2020-2030 Sishun.Co.Ltd. All Rights Reserved.
 */
package com.vevor.tools.seo.sitemap.service;

import com.vevor.tools.seo.sitemap.pojo.vo.SiteMapInfoVO;

import java.util.List;

/**
 * @Description ：服务器SiteMap.XML文件上传
 * @Program ：vevor-tools-seo
 * @Author ：Li Hui
 * @Date ：Created in 2020/10/26 11:56
 * @Version ：1.0.1
 */
public interface SiteMapFtpUploadService {

    /**
     *非产品类sitemap生成
     *
     * @param siteMapInfoVO 对外开放所需参数
     * @return boolean 上传后的文件信息
     */
    List<String> siteMapUpload(SiteMapInfoVO siteMapInfoVO);

    /**
     *非产品类sitemap生成
     *
     * @param siteMapInfoVO 对外开放所需参数
     * @return boolean 上传结果
     */
    List<String> siteMapNoImageUpload( SiteMapInfoVO siteMapInfoVO);

}
