/*
 * Copyright (c) 2020-2030 Sishun.Co.Ltd. All Rights Reserved.
 */
package com.vevor.tools.seo.sitemap.pojo.vo;

import lombok.Data;

import java.util.LinkedList;

/**
 * @author ：Li Hui
 * @version ：S1.0
 * @description ：sitemap 接口所需 url 信息
 * @program ：vevor-seo-fileupload
 * @date ：Created in 2020/10/19 14:19
 */
@Data
public class SiteMapInfoVO {

    /**
     * 服务器地址
     */
    private String ip;

    /**
     * siteMap域名地址所属语种
     */
    private String language;

    /**
     * url所属页面类型名称，决定sitemap的生成文件名
     */
    private String pageTypeName;

    /**
     * 非产品url集合
     */
    private LinkedList<String> urls;

    /**
     * url 服务器域名
     */
    private String domain;

    /**
     * 产品化url<商品页面url,商品封面图url>
     * Tip:****url之间用逗号隔开****
     */
    private LinkedList<String> imageUrl;

}
