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
 * @Date ：Created in 2020/10/23 9:39
 * @Version ：1.0.1
 */

public class WebSitemapUrl implements ISitemapUrl {
    private final URL url;
    private final Date lastMod;
    private final ChangeFreq changeFreq;
    private final Double priority;

    /** Encapsulates a single simple URL */
    public WebSitemapUrl(String url) throws MalformedURLException {
        this(new URL(url));
    }

    /** Encapsulates a single simple URL */
    public WebSitemapUrl(URL url) {
        this.url = url;
        this.lastMod = null;
        this.changeFreq = null;
        this.priority = null;
    }

    /** Creates an URL with configured options */
    public WebSitemapUrl(OptionsImage options) {
        this((AbstractImageSitemapUrlOptions<?,?>)options);
    }

    WebSitemapUrl(AbstractImageSitemapUrlOptions<?,?> options) {
        this.url = options.url;
        this.lastMod = options.lastMod;
        this.changeFreq = options.changeFreq;
        this.priority = options.priority;
    }

    @Override
    public Date getLastMod() { return lastMod; }
    public ChangeFreq getChangeFreq() { return changeFreq; }
    public Double getPriority() { return priority; }
    @Override
    public URL getUrl() { return url; }

    /** Options to configure web sitemap URLs */
    public static class OptionsImage extends AbstractImageSitemapUrlOptions<WebSitemapUrl, OptionsImage> {

        /** Configure this URL */
        public OptionsImage(String url)throws MalformedURLException {
            this(new URL(url));
        }

        /** Configure this URL */
        public OptionsImage(URL url) {
            super(url, WebSitemapUrl.class);
        }

    }
}
