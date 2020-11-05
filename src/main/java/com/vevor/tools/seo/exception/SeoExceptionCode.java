/*
 * Copyright (c) 2020-2030 Sishun.Co.Ltd. All Rights Reserved.
 */
package com.vevor.tools.seo.exception;

import com.vevor.common.exception.code.AbstractExceptionCode;
import com.vevor.common.exception.code.ExceptionBody;
import org.springframework.stereotype.Component;

/**
 * @Description ：Seo文件生成异常编码
 * @Program ：vevor-tools
 * @Author ：Li Hui
 * @Date ：Created in 2020/10/29 13:21
 * @Version ：1.0.1
 */
@Component
public class SeoExceptionCode extends AbstractExceptionCode {

    public final ExceptionBody SER_ERR_MalformedURL = new ExceptionBody(this.getSystem(), 50010, "文件生成异常，请联系管理员",

            "SiteMap "+"UrlGenerator "+"Error , please try again later or contact the administrator");

    public final ExceptionBody SER_ERR_ROBOT_UNKNOWN = new ExceptionBody(this.getSystem(), 50011, "robot生成未知错误",
            "Robot "+"Generator "+"Unknown " +
            "error");

    @Override
    protected int getSystem() {
        return 0;
    }
}
