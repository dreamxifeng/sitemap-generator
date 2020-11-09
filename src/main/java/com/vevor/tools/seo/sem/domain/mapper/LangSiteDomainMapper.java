/*
 * Copyright (c) 2020-2030 Sishun.Co.Ltd. All Rights Reserved.
 */

package com.vevor.tools.seo.sem.domain.mapper;

import com.vevor.tools.seo.sem.domain.dataobject.LangSiteDomainDO;
import com.vevor.tools.seo.sem.domain.dataobject.example.LangSiteDomainDOExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

 /**
 * @author ：Li Hui
 * @version ：M1.0
 * @program ：tools-seo-server
 * @date ：Created in 2020/11/06 14:29
 * @description ： SeoLangSiteDomainMapper
 */
 @Mapper
public interface LangSiteDomainMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    LangSiteDomainDO queryById(Integer id);

    /**
     * 通过ID集合查询多条数据
     *
     * @param ids 主键集合
     * @return 实例对象集合
     */
    List<LangSiteDomainDO> queryByIds(@Param("ids") List<Integer> ids);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param langSiteDomainDO 实例对象
     * @return 对象列表
     */
    List<LangSiteDomainDO> queryAll(LangSiteDomainDO langSiteDomainDO);

    /**
     * 新增可选数据
     * @param langSiteDomainDO 实例对象
     * @return 影响行数
     */
    int insertSelective(LangSiteDomainDO langSiteDomainDO);

    /**
     * 批量新增数据
     *
     * @param langSiteDomainDOList 实例对象集合
     * @return 影响行数
     */
    int insertBatch(@Param("dataObjectList") List<LangSiteDomainDO> langSiteDomainDOList);

    /**
     * 修改数据
     *
     * @param langSiteDomainDO 实例对象
     * @return 影响行数
     */
    int update(LangSiteDomainDO langSiteDomainDO);

    /**
     * 批量修改数据
     *
     * @param langSiteDomainDOList 实例对象集合
     * @return 影响行数
     */
    int updateBatch(@Param("dataObjectList") List<LangSiteDomainDO> langSiteDomainDOList);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 通过主键集合删除数据
     *
     * @param ids 主键集合
     * @return 影响行数
     */
    int deleteByIds(@Param("ids") List<Integer> ids);


        /**
     * 通过主键逻辑删除数据,0:未删除,1:已删除
     *
     * @param id 主键
     * @return 影响行数
     */
    int logicDeleteById(Integer id);

    /**
     * 通过主键集合逻辑删除数据,0:未删除,1:已删除
     *
     * @param ids 主键集合
     * @return 影响行数
     */
    int logicDeleteByIds(@Param("ids") List<Integer> ids);

   /**
     * 通过example查询count
     *
     * @param example 组合条件
     * @return count
     */
    long countByExample(LangSiteDomainDOExample example);

   /**
     * 通过example删除对象
     * @param example 组合条件
     * @return 影响的行数
     */
    int deleteByExample(LangSiteDomainDOExample example);

    /**
     * 通过example查询对象列表
     *
     * @param example 组合条件
     * @return 对象列表
     */
    List<LangSiteDomainDO> queryByExample(LangSiteDomainDOExample example);
}
