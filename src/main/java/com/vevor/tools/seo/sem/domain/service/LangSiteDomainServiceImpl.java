/*
 * Copyright (c) 2020-2030 Sishun.Co.Ltd. All Rights Reserved.
 */
package com.vevor.tools.seo.sem.domain.service;

import com.vevor.tools.seo.sem.domain.dataobject.LangSiteDomainDO;
import com.vevor.tools.seo.sem.domain.dataobject.example.LangSiteDomainDOExample;
import com.vevor.tools.seo.sem.domain.mapper.LangSiteDomainExtMapper;
import com.vevor.tools.seo.untils.regex.RegexUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description ：语种站点域名实现类
 * @Program ：tools-seo-server
 * @Author ：Li Hui
 * @Date ：Created in 2020/11/6 14:41
 * @Version ：
 */
@Service
public class LangSiteDomainServiceImpl {

    @Autowired
    private LangSiteDomainExtMapper langSiteDomainMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */

    public LangSiteDomainDO queryById(Integer id) {
        return langSiteDomainMapper.queryById(id);
    }

    public List<LangSiteDomainDO> queryByIds(List<Integer> ids) {
        return langSiteDomainMapper.queryByIds(ids);
    }

    /**
     * 多条件查询所有
     *
     * @param langSiteDomainDO 查询参数
     * @return 返回查询结果集
     */
    public List<LangSiteDomainDO> queryAll(LangSiteDomainDO langSiteDomainDO) {
        return langSiteDomainMapper.queryAll(langSiteDomainDO);
    }

    /**
     * 新增数据
     *
     * @param langSiteDomainDO 对象
     * @return 新增是否成功
     */
    public boolean insertSelective(LangSiteDomainDO langSiteDomainDO) {
        if(langSiteDomainDO.getLang().equals(langSiteDomainMapper.queryBySite(langSiteDomainDO.getLang(),
                langSiteDomainDO.getWebsite())) && langSiteDomainMapper.queryBySite(langSiteDomainDO.getLang(),
                langSiteDomainDO.getWebsite())>=2){
            // TODO: 2020/11/6 全局异常
            throw new NullPointerException("该站点最多出现2次");
        }
        if (langSiteDomainMapper.queryByTerminal(langSiteDomainDO.getWebsite(),
                langSiteDomainDO.getLang(),
                langSiteDomainDO.getTerminal())>=1){
            // TODO: 2020/11/6 全局异常
            throw new NullPointerException("该终端在此站点内已存在");
        }
        //域名查询之前先校验域名规则
        RegexUtils.regexDomain(langSiteDomainDO.getDomainName(),langSiteDomainDO.getTerminal());
        if (langSiteDomainMapper.queryByDomain(langSiteDomainDO.getDomainName())>1){
            // TODO: 2020/11/6 全局异常
            throw new NullPointerException("此域名已经存在");
        }
        langSiteDomainDO.setCreatedTime(new Date());
        langSiteDomainDO.setUpdatedTime(new Date());
        return langSiteDomainMapper.insertSelective(langSiteDomainDO)>0;
    }


    public int insertBatch(List<LangSiteDomainDO> langSiteDomainDOList) {
        return langSiteDomainMapper.insertBatch(langSiteDomainDOList);
    }


    public int update(LangSiteDomainDO langSiteDomainDO) {
        return langSiteDomainMapper.update(langSiteDomainDO);
    }


    public int updateBatch(List<LangSiteDomainDO> langSiteDomainDOList) {
        return langSiteDomainMapper.updateBatch(langSiteDomainDOList);
    }


    public int deleteById(Integer id) {
        return langSiteDomainMapper.deleteById(id);
    }


    public int deleteByIds(List<Integer> ids) {
        return langSiteDomainMapper.deleteByIds(ids);
    }


    public int logicDeleteById(Integer id) {
        return langSiteDomainMapper.logicDeleteById(id);
    }


    public int logicDeleteByIds(List<Integer> ids) {
        return langSiteDomainMapper.logicDeleteByIds(ids);
    }


    public long countByExample(LangSiteDomainDOExample example) {
        return langSiteDomainMapper.countByExample(example);
    }


    public int deleteByExample(LangSiteDomainDOExample example) {
        return langSiteDomainMapper.deleteByExample(example);
    }


    public List<LangSiteDomainDO> queryByExample(LangSiteDomainDOExample example) {
        return langSiteDomainMapper.queryByExample(example);
    }
}
