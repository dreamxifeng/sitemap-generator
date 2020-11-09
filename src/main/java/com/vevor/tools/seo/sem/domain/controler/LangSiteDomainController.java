/*
 * Copyright (c) 2020-2030 Sishun.Co.Ltd. All Rights Reserved.
 */
package com.vevor.tools.seo.sem.domain.controler;

import com.vevor.tools.seo.sem.domain.dataobject.LangSiteDomainDO;
import com.vevor.tools.seo.sem.domain.service.LangSiteDomainServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description ：语种站点终端域名配置处理层
 * @Program ：tools-seo-server
 * @Author ：Li Hui
 * @Date ：Created in 2020/11/6 14:57
 * @Version ：M1.0
 */
@RestController
@RequestMapping(value = "/langSiteDomainController-langSiteDomainService")
public class LangSiteDomainController {

    @Autowired
    private LangSiteDomainServiceImpl langSiteDomainService;

    /**
     * 根据id查询某一条域名信息
     *
     * @param id id
     * @return 返回域名信息
     */
    @GetMapping("/queryById")
    public LangSiteDomainDO queryById(@RequestParam("id") Integer id){

        return langSiteDomainService.queryById(id);
    }

    /**
     * 条件查询所有
     *
     * @param domainDO 对象
     * @return 多条数据集合
     */
    @PostMapping("/queryAll")
    public List<LangSiteDomainDO> queryAll(@RequestBody LangSiteDomainDO domainDO){

        return langSiteDomainService.queryAll(domainDO);
    }

    /**
     * 新增数据
     *
     * @param domainDO 参数对象
     * @return 新增结果 true/false
     */
    @PostMapping("/insert")
    public boolean insertDomain(@RequestBody LangSiteDomainDO domainDO){
        // TODO: 2020/11/6 校验入参对象属性
        return  langSiteDomainService.insertSelective(domainDO);
    }

}
