/*
 * Copyright (c) 2020-2030 Sishun.Co.Ltd. All Rights Reserved.
 */
package com.vevor.tools.seo.sitemap.rewritesourcecode;

/**
 * @Description ：文件读写处理
 * @Program ：vevor-tools
 * @Author ：Li Hui
 * @Date ：Created in 2020/10/21 16:20
 * @Version ：1.0.1
 */
public enum ChangeFreq {
	ALWAYS,
	HOURLY,
	DAILY,
	WEEKLY,
	MONTHLY,
	YEARLY,
	NEVER;
	/**
	 * lowerCase
	 */
	String lowerCase;

	/**
	 * lowerCase
	 */
	private ChangeFreq() {
		lowerCase = this.name().toLowerCase();
	}

	@Override
	public String toString() {
		return lowerCase;
	}
}
