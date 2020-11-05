/*
 * Copyright (c) 2020-2030 Sishun.Co.Ltd. All Rights Reserved.
 */
package com.vevor.tools.seo.sitemap.rewritesourcecode;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Description ：文件读写处理
 * @Program ：vevor-tools
 * @Author ：Li Hui
 * @Date ：Created in 2020/10/21 16:20
 * @Version ：1.0.1
 */
public class WebSitemapGenerator extends SitemapGenerator<WebSitemapUrl, WebSitemapGenerator> {

	WebSitemapGenerator(AbstractSitemapGeneratorOptions<?> options) {
		super(options, new Renderer());
	}

	/** Configures a builder so you can specify sitemap generator options
	 *
	 * @param baseUrl All URLs in the generated sitemap(s) should appear under this base URL
	 * @param baseDir Sitemap files will be generated in this directory as either "sitemap.xml" or "sitemap1.xml" "sitemap2.xml" and so on.
	 * @return a builder; call .build() on it to make a sitemap generator
	 */
	public static SitemapGeneratorBuilder<WebSitemapGenerator> builder(URL baseUrl, File baseDir) {
		return new SitemapGeneratorBuilder<>(baseUrl, baseDir, WebSitemapGenerator.class);
	}

	/** Configures a builder so you can specify sitemap generator options
	 *
	 * @param baseUrl All URLs in the generated sitemap(s) should appear under this base URL
	 * @param baseDir Sitemap files will be generated in this directory as either "sitemap.xml" or "sitemap1.xml" "sitemap2.xml" and so on.
	 * @return a builder; call .build() on it to make a sitemap generator
	 */
	public static SitemapGeneratorBuilder<WebSitemapGenerator> builder(String baseUrl, File baseDir) throws MalformedURLException {
		return new SitemapGeneratorBuilder<>(baseUrl, baseDir, WebSitemapGenerator.class);
	}

	/**Configures the generator with a base URL and directory to write the sitemap files.
	 *
	 * @param baseUrl All URLs in the generated sitemap(s) should appear under this base URL
	 * @param baseDir Sitemap files will be generated in this directory as either "sitemap.xml" or "sitemap1.xml" "sitemap2.xml" and so on.
	 */

	public WebSitemapGenerator(String baseUrl, File baseDir)
			throws MalformedURLException {
		this(new SitemapGeneratorOptions(new URL(baseUrl), baseDir));
	}

	/**Configures the generator with a base URL and directory to write the sitemap files.
	 *
	 * @param baseUrl All URLs in the generated sitemap(s) should appear under this base URL
	 * @param baseDir Sitemap files will be generated in this directory as either "sitemap.xml" or "sitemap1.xml" "sitemap2.xml" and so on.
	 */
	public WebSitemapGenerator(URL baseUrl, File baseDir) {
		this(new SitemapGeneratorOptions(baseUrl, baseDir));
	}

	/**Configures the generator with a base URL and a null directory. The object constructed
	 * is not intended to be used to write to files. Rather, it is intended to be used to obtain
	 * XML-formatted strings that represent sitemaps.
	 *
	 * @param baseUrl All URLs in the generated sitemap(s) should appear under this base URL
	 */
	public WebSitemapGenerator(String baseUrl) throws MalformedURLException {
		this(new SitemapGeneratorOptions(new URL(baseUrl)));
	}

	/**Configures the generator with a base URL and a null directory. The object constructed
	 * is not intended to be used to write to files. Rather, it is intended to be used to obtain
	 * XML-formatted strings that represent sitemaps.
	 *
	 * @param baseUrl All URLs in the generated sitemap(s) should appear under this base URL
	 */
	public WebSitemapGenerator(URL baseUrl) {
		this(new SitemapGeneratorOptions(baseUrl));
	}

	private static class Renderer extends AbstractSitemapUrlRenderer<WebSitemapUrl> implements ISitemapUrlRenderer<WebSitemapUrl> {


		@Override
		public Class<WebSitemapUrl> getUrlClass() {
			return null;
		}

		@Override
		public String getXmlNamespaces() {
			return null;
		}

		@Override
		public void render(WebSitemapUrl url, StringBuilder sb, W3CDateFormat dateFormat) {

		}
	}
}
