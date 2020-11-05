/*
 * Copyright (c) 2020-2030 Sishun.Co.Ltd. All Rights Reserved.
 */
package com.vevor.tools.seo.sitemap.rewritesourcecode;

/**
 * @Description ：源码更改
 * @Program ：vevor-seo-fileupload
 * @Author ：Li Hui
 * @Date ：Created in 2020/10/28 13:51
 * @Version ：S1.0
 */
public abstract class AbstractSitemapUrlRenderer<T extends WebSitemapUrl> implements ISitemapUrlRenderer<T> {
    public void render(WebSitemapUrl url, StringBuilder sb, W3CDateFormat dateFormat, String additionalData) {
        if (url.getUrl().toString().contains(",")){
            sb.append("  <url>\n");
            sb.append("    <loc>");
            sb.append(UrlUtils.escapeXml(url.getUrl().toString().split(",")[0]));
            sb.append("</loc>\n");
            if (url.getLastMod() != null) {
                sb.append("    <lastmod>");
                sb.append(dateFormat.format(url.getLastMod()));
                sb.append("</lastmod>\n");
            }
            if (url.getChangeFreq() != null) {
                sb.append("    <changefreq>");
                sb.append(url.getChangeFreq().toString());
                sb.append("</changefreq>\n");
            }
            if (url.getPriority() != null) {
                sb.append("    <priority>");
                sb.append(url.getPriority().toString());
                sb.append("</priority>\n");
            }
            if (additionalData != null) {
                sb.append(additionalData);
            }
        }else {
            sb.append("  <url>\n");
            sb.append("    <loc>");
            sb.append(UrlUtils.escapeXml(url.getUrl().toString()));
            sb.append("</loc>\n");
            if (url.getLastMod() != null) {
                sb.append("    <lastmod>");
                sb.append(dateFormat.format(url.getLastMod()));
                sb.append("</lastmod>\n");
            }
            if (url.getChangeFreq() != null) {
                sb.append("    <changefreq>");
                sb.append(url.getChangeFreq().toString());
                sb.append("</changefreq>\n");
            }
            if (url.getPriority() != null) {
                sb.append("    <priority>");
                sb.append(url.getPriority().toString());
                sb.append("</priority>\n");
            }
            if (additionalData != null) {
                sb.append(additionalData);
            }
        }
        sb.append("  </url>\n");
    }

    public void renderTag(StringBuilder sb, String namespace, String tagName, Object value) {
        if (value == null) {
            return;
        }
        sb.append("      <");
        sb.append(namespace);
        sb.append(':');
        sb.append(tagName);
        sb.append('>');
        sb.append(UrlUtils.escapeXml(value.toString()));
        sb.append("</");
        sb.append(namespace);
        sb.append(':');
        sb.append(tagName);
        sb.append(">\n");
    }

    public void renderSubTag(StringBuilder sb, String namespace, String tagName, Object value) {
        if (value == null) {
            return;
        }
        sb.append("  ");
        renderTag(sb, namespace, tagName, value);
    }

}
