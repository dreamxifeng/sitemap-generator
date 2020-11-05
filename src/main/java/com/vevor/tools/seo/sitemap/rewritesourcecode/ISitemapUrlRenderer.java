/*
 * Copyright (c) 2020-2030 Sishun.Co.Ltd. All Rights Reserved.
 */
package com.vevor.tools.seo.sitemap.rewritesourcecode;

/**
 * @Description ：源码更改
 * @Program ：vevor-tools
 * @Author ：Li Hui
 * @Date ：Created in 2020/10/23 9:59
 * @Version ：1.0.1
 */
public interface ISitemapUrlRenderer <T extends ISitemapUrl>{

     Class<T> getUrlClass();
     String getXmlNamespaces();

     /**
      *      自定义xml 标签头
      * @param url url
      * @param sb StringBuilder
      * @param dateFormat W3CDateFormat
      */
     void render(T url, StringBuilder sb, W3CDateFormat dateFormat);
}
