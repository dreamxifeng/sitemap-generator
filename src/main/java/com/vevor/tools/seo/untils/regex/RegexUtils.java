/*
 * Copyright (c) 2020-2030 Sishun.Co.Ltd. All Rights Reserved.
 */
package com.vevor.tools.seo.untils.regex;

import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description ：正则校验
 * @Program ：tools-seo-server
 * @Author ：Li Hui
 * @Date ：Created in 2020/11/9 16:32
 * @Version ：1.0
 */
@Slf4j
public class RegexUtils {

    private static final String SIGN = "/";
    private static final String SUFFIX = "html";
    private static final String REG_PC = "^(www\\.)([a-z0-9]{1,63}\\.)([a-z0-9]{1,63})+(\\.[a-z0-9]{1,63}){0,2}$";
    private static final String REG_M = "^(m\\.)([a-z0-9]{1,63}\\.)([a-z0-9]{1,63})+(\\.[a-z0-9]{1,63}){0,2}$";


    public static boolean regexDomain(String str,int terminal) {
        String reg = "";
        if (terminal ==0){
            reg = RegexUtils.REG_PC;
        }
        if (terminal == 1){
            reg = RegexUtils.REG_M;
        }
        Pattern p = Pattern.compile(reg);
        Matcher matcher = p.matcher(str);
        if (!matcher.matches()) {
            // TODO: 2020/11/9 校验 不通过 抛出异常
            log.info("444ddd");
        }
        String[] split = str.split("\\.");
        if (split[split.length - 1].endsWith(RegexUtils.SIGN) || split[split.length-1].equals(RegexUtils.SUFFIX)){
            // TODO: 2020/11/9 校验 不通过 抛出异常
            log.info("444");
        }
        return true;
    }

}
