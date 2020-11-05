/*
 * Copyright (c) 2020-2030 Sishun.Co.Ltd. All Rights Reserved.
 */
package com.vevor.tools.seo.sitemap.rewritesourcecode;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description ：源码更改
 * @Program ：vevor-seo-fileupload
 * @Author ：Li Hui
 * @Date ：Created in 2020/10/23 8:28
 * @Version ：S1.0
 */
public class GoogleNoImageSitemapUrl extends WebSitemapUrl {
    /** ImageOptions to configure URLs with alternates */
    public static class ImageOptions extends AbstractImageSitemapUrlOptions<GoogleNoImageSitemapUrl,
            ImageOptions> {
        private final Map<URI, Map<String, String>> alternates;

        private static Map<URI, Map<String, String>> convertAlternates(final Map<String, Map<String, String>> alternates)
                throws URISyntaxException {

            final Map<URI, Map<String, String>> converted = new LinkedHashMap<URI, Map<String, String>>();
            for (final Map.Entry<String, Map<String, String>> entry : alternates.entrySet()) {
                converted.put(new URI(entry.getKey()), new LinkedHashMap<String, String>(entry.getValue()));
            }
            return converted;
        }

        /**
         * ImageOptions constructor with the alternates configurations
         *
         * @param url Base URL into which we will be adding alternates
         * @param alternates Map&lt;String, Map&lt;String, String&gt;&gt; where the key is the href and
         *					the value is a generic Map&lt;String, String&gt; holding the attributes of
         *					the link (e.g. hreflang, media, ...)
         */
        public ImageOptions(final String url, final Map<String, Map<String, String>> alternates) throws URISyntaxException, MalformedURLException {

            this(new URL(url), convertAlternates(alternates));
        }

        /**
         * ImageOptions constructor with the alternates configurations
         *
         * @param url Base URL into which we will be adding alternates
         * @param alternates Map&lt;URL, Map&lt;String, String&gt;&gt; where the key is the href and
         *					the value is a generic Map&lt;String, String&gt; holding the attributes of
         *					the link (e.g. hreflang, media, ...)
         */
        public ImageOptions(final URL url, final Map<URI, Map<String, String>> alternates) {
            super(url, GoogleNoImageSitemapUrl.class);

            this.alternates = new LinkedHashMap<URI, Map<String, String>>(alternates);
        }
    }

    private final Map<URI, Map<String, String>> alternates;

    /**
     * Constructor specifying the URL and the alternates configurations with ImageOptions object
     *
     * @param imageOptions Configuration object to initialize the GoogleImageSitemapUrl with.
     * @see ImageOptions#ImageOptions(String, Map)
     */
    public GoogleNoImageSitemapUrl(final ImageOptions imageOptions) {
        super(imageOptions);
        alternates = imageOptions.alternates;
    }

    /**
     * Constructor specifying the URL as a String and the alternates configurations
     *
     * @param url Base URL into which we will be adding alternates
     * @param alternates Map&lt;String, Map&lt;String, String&gt;&gt; where the key is the href and
     *					the value is a generic Map&lt;String, String&gt; holding the attributes of
     *					the link (e.g. hreflang, media, ...)
     */
    public GoogleNoImageSitemapUrl(final String url, final Map<String, Map<String, String>> alternates) throws URISyntaxException, MalformedURLException {
        this(new ImageOptions(url, alternates));
    }

    /**
     * Constructor specifying the URL as a URL and the alternates configurations
     *
     * @param url Base URL into which we will be adding alternates
     * @param alternates Map&lt;String, Map&lt;String, String&gt;&gt; where the key is the href and
     *					the value is a generic Map&lt;String, String&gt; holding the attributes of
     *					the link (e.g. hreflang, media, ...)
     */
    public GoogleNoImageSitemapUrl(final URL url, final Map<URI, Map<String, String>> alternates) {
        this(new ImageOptions(url, alternates));
    }

    public Map<URI, Map<String, String>> getAlternates() {
        return this.alternates;
    }

}
