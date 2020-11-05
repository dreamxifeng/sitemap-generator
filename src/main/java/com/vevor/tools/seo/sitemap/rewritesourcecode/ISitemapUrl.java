/*
 * Copyright (c) 2020-2030 Sishun.Co.Ltd. All Rights Reserved.
 */
package com.vevor.tools.seo.sitemap.rewritesourcecode;

import java.net.URL;
import java.util.Date;

/**
 * @Description ：源码更改
 * @Program ：vevor-tools
 * @Author ：Li Hui
 * @Date ：Created in 2020/10/23 9:59
 * @Version ：1.0.1
 */
public interface ISitemapUrl {

	public abstract Date getLastMod();

	public abstract URL getUrl();

}
