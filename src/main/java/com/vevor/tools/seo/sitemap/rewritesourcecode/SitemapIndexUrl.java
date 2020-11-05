/*
 * Copyright (c) 2020-2030 Sishun.Co.Ltd. All Rights Reserved.
 */
package com.vevor.tools.seo.sitemap.rewritesourcecode;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

/**
 * @Description ：源码更改
 * @Program ：vevor-tools
 * @Author ：Li Hui
 * @Date ：Created in 2020/10/26 11:58
 * @Version ：1.0.1
 */
public class SitemapIndexUrl {
    final URL url;
    final Date lastMod;
    /** Configures the sitemap URL with a specified lastMod */
    public SitemapIndexUrl(URL url, Date lastMod) {
        this.url = url;
        this.lastMod = lastMod;
    }

    /** Configures the sitemap URL with a specified lastMod */
    public SitemapIndexUrl(String url, Date lastMod) throws MalformedURLException {
        this(new URL(url), lastMod);
    }

    /** Configures the sitemap URL with no specified lastMod; we'll use
     *  {@link SitemapIndexGenerator.Options#defaultLastMod(Date)} or leave it blank if no default is specified */
    public SitemapIndexUrl(URL url) {
        this(url, null);
    }

    /** Configures the sitemap URL with no specified lastMod; we'll use {@link SitemapIndexGenerator.Options#defaultLastMod(Date)} or leave it blank if no default is specified */
    public SitemapIndexUrl(String url) throws MalformedURLException {
        this(new URL(url));
    }
}
