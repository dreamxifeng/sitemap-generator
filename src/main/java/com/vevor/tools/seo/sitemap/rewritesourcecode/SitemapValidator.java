/*
 * Copyright (c) 2020-2030 Sishun.Co.Ltd. All Rights Reserved.
 */
package com.vevor.tools.seo.sitemap.rewritesourcecode;

import lombok.extern.slf4j.Slf4j;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Description ：文件读写处理
 * @Program ：vevor-tools
 * @Author ：Li Hui
 * @Date ：Created in 2020/10/21 16:20
 * @Version ：1.0.1
 */
@Slf4j
public class SitemapValidator  {

	// support gzip
	// confirm < 10MB
	// confirm single host
	// confirm correct host
	// confirm UTF-8

	// support Mobile/Geo/Video/Code/News (sitemap.xsd doesn't support them)
		// confirm mobile restrictions: no non-mobile urls
		// confirm news restrictions: 3 days, 1000 URLs
		// video restrictions: title, player_loc/content_loc, no non-video urls
		//IMO news should have no non-news urls, geo should have no non-geo urls, code should have no non-code urls

	private static Schema sitemapSchema, sitemapIndexSchema;

	private synchronized static void lazyLoad() {
		if (sitemapSchema != null)  {
			return;
		}
		SchemaFactory factory =
			SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		try {
			//读取xsd文件
			InputStream seteindexInput = SitemapValidator.class.getClassLoader().getResourceAsStream("sitemap/siteindex.xsd");
			InputStream setemapInput = SitemapValidator.class.getClassLoader().getResourceAsStream("sitemap/sitemap.xsd");
			sitemapSchema = lazyLoad(factory, setemapInput);
			sitemapIndexSchema = lazyLoad(factory, seteindexInput);
		} catch (Exception e) {
			throw new RuntimeException("BUG", e);
		}
	}

	private synchronized static Schema lazyLoad(SchemaFactory factory, InputStream stream) throws IOException, SAXException {

		try {

			if (stream == null) {
				throw new RuntimeException("BUG Couldn't load " );
			}
			StreamSource source = new StreamSource(stream);
			return factory.newSchema(source);
		} finally {
			if(stream != null) {
				stream.close();
			}
		}

	}

	/** Validates an ordinary web sitemap file (NOT a Google-specific sitemap) */
	public static void validateWebSitemap(File sitemap) throws SAXException {
		lazyLoad();
		validateXml(sitemap, sitemapSchema);
	}

	/** Validates a sitemap index file  */
	public static void validateSitemapIndex(File sitemap) throws SAXException {
		lazyLoad();
		validateXml(sitemap, sitemapIndexSchema);
	}

	private static void validateXml(File sitemap, Schema schema) throws SAXException {
		try {
			Validator validator = schema.newValidator();
			FileReader reader = null;
			try {
				reader = new FileReader(sitemap);
				SAXSource source = new SAXSource(new InputSource(reader));
				validator.validate(source);
			} catch (IOException e) {
				throw new RuntimeException(e);
			} finally {
				if(reader != null) {
					reader.close();
				}
			}
		} catch (IOException ex) {
			throw new RuntimeException("Unable to close stream.", ex);
		}

	}

}
