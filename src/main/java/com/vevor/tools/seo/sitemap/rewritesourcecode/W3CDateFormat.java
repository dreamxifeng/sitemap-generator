/*
 * Copyright (c) 2020-2030 Sishun.Co.Ltd. All Rights Reserved.
 */
package com.vevor.tools.seo.sitemap.rewritesourcecode;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import static java.util.Calendar.*;

/**
 * @Description ：W3CDateFormat
 * @Program ：vevor-tools
 * @Author ：Li Hui
 * @Date ：Created in 2020/10/21 16:20
 * @Version ：1.0.1
 */
public class W3CDateFormat extends SimpleDateFormat {
	private static final long serialVersionUID = -5733368073260485802L;

	/** The six patterns defined by W3C, plus {@link #AUTO} configuration */
	public enum Pattern {
		/** "yyyy-MM-dd'T'HH:mm:ss.SSSZ" */
		MILLISECOND("yyyy-MM-dd'T'HH:mm:ss.SSSZ", true),
		/** "yyyy-MM-dd'T'HH:mm:ssZ" */
		SECOND("yyyy-MM-dd'T'HH:mm:ssZ", true),
		/** "yyyy-MM-dd'T'HH:mmZ" */
		MINUTE("yyyy-MM-dd'T'HH:mmZ", true),
		/** "yyyy-MM-dd" */
		DAY("yyyy-MM-dd", false),
		/** "yyyy-MM" */
		MONTH("yyyy-MM", false),
		/** "yyyy" */
		YEAR("yyyy", false),
		/** Automatically compute the right pattern to use */
		AUTO("", true);

		private final String pattern;
		private final boolean includeTimeZone;

		Pattern(String pattern, boolean includeTimeZone) {
			this.pattern = pattern;
			this.includeTimeZone = includeTimeZone;
		}
	}

	private final Pattern pattern;
	/** The GMT ("zulu") time zone, for your convenience */
	public static final TimeZone ZULU = TimeZone.getTimeZone("GMT");

	/** Build a formatter in AUTO mode */
	public W3CDateFormat() {
		this(Pattern.AUTO);
	}

	/** Build a formatter using the specified Pattern, or AUTO mode */
	public W3CDateFormat(Pattern pattern) {
		super(pattern.pattern);
		this.pattern = pattern;
	}

	/** This is what you override when you extend DateFormat; use {@link DateFormat#format(Date)} instead */
	@Override
	public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition pos) {
		boolean includeTimeZone = pattern.includeTimeZone;
		if (pattern == Pattern.AUTO) {
			includeTimeZone = autoFormat(date);
		}
		super.format(date, toAppendTo, pos);
		if (includeTimeZone) {
			convertRfc822TimeZoneToW3c(toAppendTo);
		}
		return toAppendTo;
	}

	private boolean applyPattern(Pattern pattern) {
		applyPattern(pattern.pattern);
		return pattern.includeTimeZone;
	}

	private boolean autoFormat(Date date) {
		if (calendar == null) {
			calendar = new GregorianCalendar();
		}
		calendar.setTime(date);
		boolean hasMillis = calendar.get(MILLISECOND) > 0;
		if (hasMillis) {
			return applyPattern(Pattern.MILLISECOND);
		}
		boolean hasSeconds = calendar.get(SECOND) > 0;
		if (hasSeconds) {
			return applyPattern(Pattern.SECOND);
		}
		boolean hasTime = (calendar.get(HOUR_OF_DAY) + calendar.get(MINUTE)) > 0;
		if (hasTime) {
			return applyPattern(Pattern.MINUTE);
		}
		return applyPattern(Pattern.DAY);
	}

	/** This is what you override when you extend DateFormat; use {@link DateFormat#parse(String)} instead */
	@Override
	public Date parse(String text, ParsePosition pos) {
		text = convertW3cTimeZoneToRfc822(text);
		if (pattern == Pattern.AUTO) {
			return autoParse(text, pos);
		}
		return super.parse(text, pos);
	}

	private Date autoParse(String text, ParsePosition pos) {
		for (Pattern pattern : Pattern.values()) {
			if (pattern == Pattern.AUTO) {
				continue;
			}
			applyPattern(pattern);
			Date out = super.parse(text, pos);
			if (out != null) {
				return out;
			}
		}
		return null; // this will force a ParseException
	}

	private void convertRfc822TimeZoneToW3c(StringBuffer toAppendTo) {
		int length = toAppendTo.length();
		if (ZULU.equals(calendar.getTimeZone())) {
			toAppendTo.replace(length - 5, length, "Z");
		} else {
			toAppendTo.insert(length - 2, ':');
		}
	}

	private String convertW3cTimeZoneToRfc822(String source) {
		int length = source.length();
		if (source.endsWith("Z")) {
			return source.substring(0, length-1) + "+0000";
		}
		if (source.charAt(length-3) == ':') {
			return source.substring(0, length-3) + source.substring(length - 2);
		}
		return source;
	}

}
