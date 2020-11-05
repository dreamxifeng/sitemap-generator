/*
 * Copyright (c) 2020-2030 Sishun.Co.Ltd. All Rights Reserved.
 */
package com.vevor.tools.seo.sitemap.rewritesourcecode;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Description ：源码更改
 * @Program ：vevor-tools
 * @Author ：Li Hui
 * @Date ：Created in 2020/10/23 10:32
 * @Version ：1.0.1
 */
public class SitemapGeneratorOptions extends AbstractSitemapGeneratorOptions<SitemapGeneratorOptions> {
    public SitemapGeneratorOptions(URL baseUrl, File baseDir) {
        super(baseUrl, baseDir);
    }

    public SitemapGeneratorOptions(String baseUrl, File baseDir) throws MalformedURLException {
        this(new URL(baseUrl), baseDir);
    }

    public SitemapGeneratorOptions(URL baseUrl) {
        super(baseUrl);
    }

    public SitemapGeneratorOptions(String baseUrl) throws MalformedURLException {
        super(new URL(baseUrl));
    }
}
