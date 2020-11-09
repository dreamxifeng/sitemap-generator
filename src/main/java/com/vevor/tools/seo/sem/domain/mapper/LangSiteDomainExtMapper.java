/*
 * Copyright (c) 2020-2030 Sishun.Co.Ltd. All Rights Reserved.
 */

package com.vevor.tools.seo.sem.domain.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author ：Li Hui
 * @version ：M1.0
 * @program ：tools-seo-server
 * @date ：Created in 2020/11/06 14:29
 * @description ： SeoLangSiteDomainExtMapper
 */
@Mapper
public interface LangSiteDomainExtMapper extends LangSiteDomainMapper {


  /**
   * 根据语种站点查询使用次数
   *
   * @param lang 语种
   * @param website 站点
   * @return 使用次数
   */
  Integer queryBySite(@Param("lang")Integer lang,@Param("website")String website);

  /**
   * 根据域名查询是否存在
   *
   * @param domainName 域名
   * @return 使用次数
   */
   Integer queryByDomain(@Param("domainName")String domainName);

  /**
   * 根据终端查询当前站点终端数
   *
   * @param lang 语种
   * @param website 站点
   * @param terminal 终端
   * @return 返回站点包含的终端数
   */
   Integer queryByTerminal(@Param("website")String website,@Param("lang")Integer lang,
                           @Param("terminal")Integer terminal);

}
