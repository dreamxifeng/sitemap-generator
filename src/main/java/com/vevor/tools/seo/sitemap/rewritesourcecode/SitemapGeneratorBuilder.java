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
 * @Date ：Created in 2020/10/23 16:34
 * @Version ：1.0.1
 */
public class SitemapGeneratorBuilder <G extends SitemapGenerator<?,?>> extends AbstractSitemapGeneratorOptions<SitemapGeneratorBuilder<G>> {


    Class<G> sitemapGeneratorClass;

    /**Configures the generator with a base URL and directory to write the sitemap files.
     *
     * @param baseUrl All URLs in the generated sitemap(s) should appear under this base URL
     * @param baseDir Sitemap files will be generated in this directory as either "sitemap.xml" or "sitemap1.xml" "sitemap2.xml" and so on.
     * @param sitemapGeneratorClass the class of the generator the builder will create
     */
    public SitemapGeneratorBuilder(URL baseUrl, File baseDir, Class<G> sitemapGeneratorClass) {
        super(baseUrl, baseDir);
        this.sitemapGeneratorClass = sitemapGeneratorClass;
    }

    /**Configures the generator with a base URL and directory to write the sitemap files.
     *
     * @param baseUrl All URLs in the generated sitemap(s) should appear under this base URL
     * @param baseDir Sitemap files will be generated in this directory as either "sitemap.xml" or "sitemap1.xml" "sitemap2.xml" and so on.
     * @param sitemapGeneratorClass the class of the generator the builder will create
     */
    public SitemapGeneratorBuilder(String baseUrl, File baseDir, Class<G> sitemapGeneratorClass) throws MalformedURLException {
        this(new URL(baseUrl), baseDir, sitemapGeneratorClass);
    }

    /** Constructs a sitemap generator configured with the options you specified */
    public G build() {
        try {
            return sitemapGeneratorClass.getDeclaredConstructor(AbstractSitemapGeneratorOptions.class).newInstance(this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }




}
