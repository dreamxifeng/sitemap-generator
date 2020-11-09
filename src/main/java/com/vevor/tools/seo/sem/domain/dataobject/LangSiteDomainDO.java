/*
 * Copyright (c) 2020-2030 Sishun.Co.Ltd. All Rights Reserved.
 */

package com.vevor.tools.seo.sem.domain.dataobject;

import java.util.Date;
import java.io.Serializable;
import lombok.Data;

import javax.validation.constraints.Pattern;

/**
 * @author ：Li Hui
 * @version ：M1.0
 * @program ：tools-seo-server
 * @date ：Created in 2020/11/06 14:29
 * @description ：实体类 SeoLangSiteDomainDO
 */
@Data
public class LangSiteDomainDO implements Serializable {
    private static final long serialVersionUID = 424247967966525813L;
    /**
    * 主键id
    */
    private Integer id;
    /**
    * 语种(0:en1:fr2:..)
    */
    private Integer lang;
    /**
    * 站点(全球站,英国站..)
    */
    private String website;
    /**
    * 终端(0:PC1:M)
    */
    private Integer terminal;
    /**
    * 域名网址
    */
    private String domainName;
    /**
    * 域名ip地址
    */
    private String domainIp;
    /**
    * 创建人
    */
    private Integer createdBy;
    /**
    * 创建时间
    */
    private Date createdTime;
    /**
    * 修改人
    */
    private Integer updatedBy;
    /**
    * 修改时间
    */
    private Date updatedTime;
    /**
    * 是否删除(0:未删除1:已删除)
    */
    private Integer delete;

}
