/*
 * Copyright (c) 2020-2030 Sishun.Co.Ltd. All Rights Reserved.
 */
package com.vevor.tools.seo.sitemap.rewritesourcecode;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Map;

/**
 * @Description ：自定义非产品类 sitemap 生成器
 * @Program ：vevor-tools
 * @Author ：Li Hui
 * @Date ：Created in 2020/10/27 20:00
 * @Version ：1.0.1
 */
public class SitemapNoImageGenerator extends SitemapGenerator<GoogleNoImageSitemapUrl, SitemapNoImageGenerator> {

    private static class Renderer extends AbstractSitemapUrlRenderer<GoogleNoImageSitemapUrl>
            implements ISitemapUrlRenderer<GoogleNoImageSitemapUrl> {
        @Override
        public void render(final GoogleNoImageSitemapUrl url, StringBuilder sb, W3CDateFormat dateFormat) {
            final StringBuilder tagSb = new StringBuilder();
            for (final Map.Entry<URI, Map<String, String>> entry : url.getAlternates().entrySet()) {

                tagSb.append("    <xhtml:link");
                tagSb.append(" rel=\"alternate\"");
                for(final Map.Entry<String, String> innerEntry : entry.getValue().entrySet()){
                    tagSb.append(" ").append(innerEntry.getKey()).append("=\"").append(innerEntry.getValue()).append("\"");
                }
                tagSb.append(" href=\"").append(UrlUtils.escapeXml(entry.getKey().toString())).append("\"");
                tagSb.append("/>\n");
            }
            super.render(url, sb, dateFormat, tagSb.toString());
        }

        @Override
        public Class getUrlClass() {
            return GoogleNoImageSitemapUrl.class;
        }

        @Override
        public String getXmlNamespaces() {
            return null;
        }

    }
        /**
         * Configures a builder so you can specify sitemap generator options
         *
         * @param baseUrl
         *			All URLs in the generated sitemap(s) should appear under this base URL
         * @param baseDir
         *			Sitemap files will be generated in this directory as either "sitemap.xml" or
         *			"sitemap1.xml" "sitemap2.xml" and so on.
         * @return a builder; call .build() on it to make a sitemap generator
         */
        public static SitemapGeneratorBuilder<SitemapNoImageGenerator> builder(final String baseUrl, final File baseDir)
                throws MalformedURLException {

            return new SitemapGeneratorBuilder<SitemapNoImageGenerator>(baseUrl, baseDir,
                    SitemapNoImageGenerator.class);
        }

        /**
         * Configures a builder so you can specify sitemap generator options
         *
         * @param baseUrl
         *			All URLs in the generated sitemap(s) should appear under this base URL
         * @param baseDir
         *			Sitemap files will be generated in this directory as either "sitemap.xml" or
         *			"sitemap1.xml" "sitemap2.xml" and so on.
         * @return a builder; call .build() on it to make a sitemap generator
         */
        public static SitemapGeneratorBuilder<SitemapNoImageGenerator> builder(final URL baseUrl, final File baseDir) {

            return new SitemapGeneratorBuilder<SitemapNoImageGenerator>(baseUrl, baseDir,
                    SitemapNoImageGenerator.class);
        }

        /**
         * Configures the generator with a base URL and a null directory. The object constructed is not
         * intended to be used to write to files. Rather, it is intended to be used to obtain
         * XML-formatted strings that represent sitemaps.
         *
         * @param baseUrl
         *			All URLs in the generated sitemap(s) should appear under this base URL
         */
        public SitemapNoImageGenerator(final String baseUrl) throws MalformedURLException {
            this(new SitemapGeneratorOptions(new URL(baseUrl)));
        }

        /**
         * Configures the generator with a base URL and directory to write the sitemap files.
         *
         * @param baseUrl
         *			All URLs in the generated sitemap(s) should appear under this base URL
         * @param baseDir
         *			Sitemap files will be generated in this directory as either "sitemap.xml" or
         *			"sitemap1.xml" "sitemap2.xml" and so on.
         * @throws MalformedURLException
         */
        public SitemapNoImageGenerator(final String baseUrl, final File baseDir) throws MalformedURLException {
            this(new SitemapGeneratorOptions(baseUrl, baseDir));
        }

        /**
         * Configures the generator with a base URL and a null directory. The object constructed is not
         * intended to be used to write to files. Rather, it is intended to be used to obtain
         * XML-formatted strings that represent sitemaps.
         *
         * @param baseUrl
         *			All URLs in the generated sitemap(s) should appear under this base URL
         */
        public SitemapNoImageGenerator(final URL baseUrl) {
            this(new SitemapGeneratorOptions(baseUrl));
        }

        /**
         * Configures the generator with a base URL and directory to write the sitemap files.
         *
         * @param baseUrl
         *			All URLs in the generated sitemap(s) should appear under this base URL
         * @param baseDir
         *			Sitemap files will be generated in this directory as either "sitemap.xml" or
         *			"sitemap1.xml" "sitemap2.xml" and so on.
         */
        public SitemapNoImageGenerator(final URL baseUrl, final File baseDir) {
            this(new SitemapGeneratorOptions(baseUrl, baseDir));
        }

        SitemapNoImageGenerator(final AbstractSitemapGeneratorOptions<?> options) {
            super(options, new Renderer());
        }

}
