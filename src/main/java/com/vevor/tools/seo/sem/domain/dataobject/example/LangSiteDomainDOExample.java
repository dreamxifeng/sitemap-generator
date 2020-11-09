/*
 * Copyright (c) 2020-2030 Sishun.Co.Ltd. All Rights Reserved.
 */

package com.vevor.tools.seo.sem.domain.dataobject.example;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @author ：Li Hui
 * @version ：M1.0
 * @program ：tools-seo-server
 * @date ：Created in 2020/11/06 14:29
 * @description ： Example类 SeoLangSiteDomainDOExample
 */
public class LangSiteDomainDOExample {

    private String orderByClause;

    private boolean distinct;

    private List<Criteria> criteriaList;

    public LangSiteDomainDOExample() {
        criteriaList = new ArrayList<>();
    }


    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getCriteriaList() {
        return criteriaList;
    }

    public void or(Criteria criteria) {
        criteriaList.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        criteriaList.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (criteriaList.isEmpty()) {
            criteriaList.add(criteria);
        }
        return criteria;
    }

    private Criteria createCriteriaInternal() {
        return new Criteria();
    }

    public void clear() {
        criteriaList.clear();
        orderByClause = null;
        distinct = false;
    }

    public OrderByClauseCriteria createOrderByClauseCriteria() {
       return new OrderByClauseCriteria(this);
    }

    public static class OrderByClauseCriteria {
        private String orderByClause = "";

        private static final String COMMA = ",";

        private LangSiteDomainDOExample langSiteDomainDOExample;

        public OrderByClauseCriteria(LangSiteDomainDOExample langSiteDomainDOExample) {
            this.langSiteDomainDOExample = langSiteDomainDOExample;
        }
           public OrderByClauseCriteria id() {
            this.orderByClause += "id";
            return this;
        }

           public OrderByClauseCriteria lang() {
            this.orderByClause += "lang";
            return this;
        }

           public OrderByClauseCriteria website() {
            this.orderByClause += "website";
            return this;
        }

           public OrderByClauseCriteria terminal() {
            this.orderByClause += "terminal";
            return this;
        }

           public OrderByClauseCriteria domainName() {
            this.orderByClause += "domain_name";
            return this;
        }

           public OrderByClauseCriteria domainIp() {
            this.orderByClause += "domain_ip";
            return this;
        }

           public OrderByClauseCriteria createdBy() {
            this.orderByClause += "created_by";
            return this;
        }

           public OrderByClauseCriteria createdTime() {
            this.orderByClause += "created_time";
            return this;
        }

           public OrderByClauseCriteria updatedBy() {
            this.orderByClause += "updated_by";
            return this;
        }

           public OrderByClauseCriteria updatedTime() {
            this.orderByClause += "updated_time";
            return this;
        }

           public OrderByClauseCriteria delete() {
            this.orderByClause += "is_delete";
            return this;
        }

           public OrderByClauseCriteria comma(){
            if(Objects.isNull(this.orderByClause)){
                return this;
            }
            this.orderByClause = this.orderByClause + COMMA;
            return this;
        }

        public void asc() {
            if(Objects.nonNull(this.orderByClause)){
                if(this.orderByClause.endsWith(COMMA)){
                    this.orderByClause = this.orderByClause.substring(0,this.orderByClause.lastIndexOf(COMMA));
                }
                langSiteDomainDOExample.orderByClause = this.orderByClause + " asc";
            }
        }

        public void desc() {
            if(Objects.nonNull(this.orderByClause)){
                if(this.orderByClause.endsWith(COMMA)){
                    this.orderByClause = this.orderByClause.substring(0,this.orderByClause.lastIndexOf(COMMA));
                }
                langSiteDomainDOExample.orderByClause = this.orderByClause + " desc";
            }
        }
    }

    protected abstract static class GeneratedCriteria {
        List<Criterion> criteria;

        GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return !criteria.isEmpty();
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }


        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLeftLike(Integer value) {
            addCriterion("id like", "%" + value, "id");
            return (Criteria) this;
        }

        public Criteria andIdRightLike(Integer value) {
            addCriterion("id like", value + "%", "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(Integer value) {
            addCriterion("id like", "%" + value + "%", "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLeftLike(Integer value) {
            addCriterion("id not like", "%" + value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotRightLike(Integer value) {
            addCriterion("id not like", value + "%", "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(Integer value) {
            addCriterion("id not like", "%" + value + "%", "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }


        public Criteria andLangIsNull() {
            addCriterion("lang is null");
            return (Criteria) this;
        }

        public Criteria andLangIsNotNull() {
            addCriterion("lang is not null");
            return (Criteria) this;
        }

        public Criteria andLangEqualTo(Integer value) {
            addCriterion("lang =", value, "lang");
            return (Criteria) this;
        }

        public Criteria andLangNotEqualTo(Integer value) {
            addCriterion("lang <>", value, "lang");
            return (Criteria) this;
        }

        public Criteria andLangGreaterThan(Integer value) {
            addCriterion("lang >", value, "lang");
            return (Criteria) this;
        }

        public Criteria andLangGreaterThanOrEqualTo(Integer value) {
            addCriterion("lang >=", value, "lang");
            return (Criteria) this;
        }

        public Criteria andLangLessThan(Integer value) {
            addCriterion("lang <", value, "lang");
            return (Criteria) this;
        }

        public Criteria andLangLessThanOrEqualTo(Integer value) {
            addCriterion("lang <=", value, "lang");
            return (Criteria) this;
        }

        public Criteria andLangLeftLike(Integer value) {
            addCriterion("lang like", "%" + value, "lang");
            return (Criteria) this;
        }

        public Criteria andLangRightLike(Integer value) {
            addCriterion("lang like", value + "%", "lang");
            return (Criteria) this;
        }

        public Criteria andLangLike(Integer value) {
            addCriterion("lang like", "%" + value + "%", "lang");
            return (Criteria) this;
        }

        public Criteria andLangNotLeftLike(Integer value) {
            addCriterion("lang not like", "%" + value, "lang");
            return (Criteria) this;
        }

        public Criteria andLangNotRightLike(Integer value) {
            addCriterion("lang not like", value + "%", "lang");
            return (Criteria) this;
        }

        public Criteria andLangNotLike(Integer value) {
            addCriterion("lang not like", "%" + value + "%", "lang");
            return (Criteria) this;
        }

        public Criteria andLangIn(List<Integer> values) {
            addCriterion("lang in", values, "lang");
            return (Criteria) this;
        }

        public Criteria andLangNotIn(List<Integer> values) {
            addCriterion("lang not in", values, "lang");
            return (Criteria) this;
        }

        public Criteria andLangBetween(Integer value1, Integer value2) {
            addCriterion("lang between", value1, value2, "lang");
            return (Criteria) this;
        }

        public Criteria andLangNotBetween(Integer value1, Integer value2) {
            addCriterion("lang not between", value1, value2, "lang");
            return (Criteria) this;
        }


        public Criteria andWebsiteIsNull() {
            addCriterion("website is null");
            return (Criteria) this;
        }

        public Criteria andWebsiteIsNotNull() {
            addCriterion("website is not null");
            return (Criteria) this;
        }

        public Criteria andWebsiteEqualTo(String value) {
            addCriterion("website =", value, "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteNotEqualTo(String value) {
            addCriterion("website <>", value, "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteGreaterThan(String value) {
            addCriterion("website >", value, "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteGreaterThanOrEqualTo(String value) {
            addCriterion("website >=", value, "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteLessThan(String value) {
            addCriterion("website <", value, "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteLessThanOrEqualTo(String value) {
            addCriterion("website <=", value, "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteLeftLike(String value) {
            addCriterion("website like", "%" + value, "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteRightLike(String value) {
            addCriterion("website like", value + "%", "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteLike(String value) {
            addCriterion("website like", "%" + value + "%", "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteNotLeftLike(String value) {
            addCriterion("website not like", "%" + value, "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteNotRightLike(String value) {
            addCriterion("website not like", value + "%", "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteNotLike(String value) {
            addCriterion("website not like", "%" + value + "%", "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteIn(List<String> values) {
            addCriterion("website in", values, "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteNotIn(List<String> values) {
            addCriterion("website not in", values, "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteBetween(String value1, String value2) {
            addCriterion("website between", value1, value2, "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteNotBetween(String value1, String value2) {
            addCriterion("website not between", value1, value2, "website");
            return (Criteria) this;
        }


        public Criteria andTerminalIsNull() {
            addCriterion("terminal is null");
            return (Criteria) this;
        }

        public Criteria andTerminalIsNotNull() {
            addCriterion("terminal is not null");
            return (Criteria) this;
        }

        public Criteria andTerminalEqualTo(Integer value) {
            addCriterion("terminal =", value, "terminal");
            return (Criteria) this;
        }

        public Criteria andTerminalNotEqualTo(Integer value) {
            addCriterion("terminal <>", value, "terminal");
            return (Criteria) this;
        }

        public Criteria andTerminalGreaterThan(Integer value) {
            addCriterion("terminal >", value, "terminal");
            return (Criteria) this;
        }

        public Criteria andTerminalGreaterThanOrEqualTo(Integer value) {
            addCriterion("terminal >=", value, "terminal");
            return (Criteria) this;
        }

        public Criteria andTerminalLessThan(Integer value) {
            addCriterion("terminal <", value, "terminal");
            return (Criteria) this;
        }

        public Criteria andTerminalLessThanOrEqualTo(Integer value) {
            addCriterion("terminal <=", value, "terminal");
            return (Criteria) this;
        }

        public Criteria andTerminalLeftLike(Integer value) {
            addCriterion("terminal like", "%" + value, "terminal");
            return (Criteria) this;
        }

        public Criteria andTerminalRightLike(Integer value) {
            addCriterion("terminal like", value + "%", "terminal");
            return (Criteria) this;
        }

        public Criteria andTerminalLike(Integer value) {
            addCriterion("terminal like", "%" + value + "%", "terminal");
            return (Criteria) this;
        }

        public Criteria andTerminalNotLeftLike(Integer value) {
            addCriterion("terminal not like", "%" + value, "terminal");
            return (Criteria) this;
        }

        public Criteria andTerminalNotRightLike(Integer value) {
            addCriterion("terminal not like", value + "%", "terminal");
            return (Criteria) this;
        }

        public Criteria andTerminalNotLike(Integer value) {
            addCriterion("terminal not like", "%" + value + "%", "terminal");
            return (Criteria) this;
        }

        public Criteria andTerminalIn(List<Integer> values) {
            addCriterion("terminal in", values, "terminal");
            return (Criteria) this;
        }

        public Criteria andTerminalNotIn(List<Integer> values) {
            addCriterion("terminal not in", values, "terminal");
            return (Criteria) this;
        }

        public Criteria andTerminalBetween(Integer value1, Integer value2) {
            addCriterion("terminal between", value1, value2, "terminal");
            return (Criteria) this;
        }

        public Criteria andTerminalNotBetween(Integer value1, Integer value2) {
            addCriterion("terminal not between", value1, value2, "terminal");
            return (Criteria) this;
        }


        public Criteria andDomainNameIsNull() {
            addCriterion("domain_name is null");
            return (Criteria) this;
        }

        public Criteria andDomainNameIsNotNull() {
            addCriterion("domain_name is not null");
            return (Criteria) this;
        }

        public Criteria andDomainNameEqualTo(String value) {
            addCriterion("domain_name =", value, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameNotEqualTo(String value) {
            addCriterion("domain_name <>", value, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameGreaterThan(String value) {
            addCriterion("domain_name >", value, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameGreaterThanOrEqualTo(String value) {
            addCriterion("domain_name >=", value, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameLessThan(String value) {
            addCriterion("domain_name <", value, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameLessThanOrEqualTo(String value) {
            addCriterion("domain_name <=", value, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameLeftLike(String value) {
            addCriterion("domain_name like", "%" + value, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameRightLike(String value) {
            addCriterion("domain_name like", value + "%", "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameLike(String value) {
            addCriterion("domain_name like", "%" + value + "%", "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameNotLeftLike(String value) {
            addCriterion("domain_name not like", "%" + value, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameNotRightLike(String value) {
            addCriterion("domain_name not like", value + "%", "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameNotLike(String value) {
            addCriterion("domain_name not like", "%" + value + "%", "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameIn(List<String> values) {
            addCriterion("domain_name in", values, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameNotIn(List<String> values) {
            addCriterion("domain_name not in", values, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameBetween(String value1, String value2) {
            addCriterion("domain_name between", value1, value2, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameNotBetween(String value1, String value2) {
            addCriterion("domain_name not between", value1, value2, "domainName");
            return (Criteria) this;
        }


        public Criteria andDomainIpIsNull() {
            addCriterion("domain_ip is null");
            return (Criteria) this;
        }

        public Criteria andDomainIpIsNotNull() {
            addCriterion("domain_ip is not null");
            return (Criteria) this;
        }

        public Criteria andDomainIpEqualTo(String value) {
            addCriterion("domain_ip =", value, "domainIp");
            return (Criteria) this;
        }

        public Criteria andDomainIpNotEqualTo(String value) {
            addCriterion("domain_ip <>", value, "domainIp");
            return (Criteria) this;
        }

        public Criteria andDomainIpGreaterThan(String value) {
            addCriterion("domain_ip >", value, "domainIp");
            return (Criteria) this;
        }

        public Criteria andDomainIpGreaterThanOrEqualTo(String value) {
            addCriterion("domain_ip >=", value, "domainIp");
            return (Criteria) this;
        }

        public Criteria andDomainIpLessThan(String value) {
            addCriterion("domain_ip <", value, "domainIp");
            return (Criteria) this;
        }

        public Criteria andDomainIpLessThanOrEqualTo(String value) {
            addCriterion("domain_ip <=", value, "domainIp");
            return (Criteria) this;
        }

        public Criteria andDomainIpLeftLike(String value) {
            addCriterion("domain_ip like", "%" + value, "domainIp");
            return (Criteria) this;
        }

        public Criteria andDomainIpRightLike(String value) {
            addCriterion("domain_ip like", value + "%", "domainIp");
            return (Criteria) this;
        }

        public Criteria andDomainIpLike(String value) {
            addCriterion("domain_ip like", "%" + value + "%", "domainIp");
            return (Criteria) this;
        }

        public Criteria andDomainIpNotLeftLike(String value) {
            addCriterion("domain_ip not like", "%" + value, "domainIp");
            return (Criteria) this;
        }

        public Criteria andDomainIpNotRightLike(String value) {
            addCriterion("domain_ip not like", value + "%", "domainIp");
            return (Criteria) this;
        }

        public Criteria andDomainIpNotLike(String value) {
            addCriterion("domain_ip not like", "%" + value + "%", "domainIp");
            return (Criteria) this;
        }

        public Criteria andDomainIpIn(List<String> values) {
            addCriterion("domain_ip in", values, "domainIp");
            return (Criteria) this;
        }

        public Criteria andDomainIpNotIn(List<String> values) {
            addCriterion("domain_ip not in", values, "domainIp");
            return (Criteria) this;
        }

        public Criteria andDomainIpBetween(String value1, String value2) {
            addCriterion("domain_ip between", value1, value2, "domainIp");
            return (Criteria) this;
        }

        public Criteria andDomainIpNotBetween(String value1, String value2) {
            addCriterion("domain_ip not between", value1, value2, "domainIp");
            return (Criteria) this;
        }


        public Criteria andCreatedByIsNull() {
            addCriterion("created_by is null");
            return (Criteria) this;
        }

        public Criteria andCreatedByIsNotNull() {
            addCriterion("created_by is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedByEqualTo(Integer value) {
            addCriterion("created_by =", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByNotEqualTo(Integer value) {
            addCriterion("created_by <>", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByGreaterThan(Integer value) {
            addCriterion("created_by >", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByGreaterThanOrEqualTo(Integer value) {
            addCriterion("created_by >=", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByLessThan(Integer value) {
            addCriterion("created_by <", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByLessThanOrEqualTo(Integer value) {
            addCriterion("created_by <=", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByLeftLike(Integer value) {
            addCriterion("created_by like", "%" + value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByRightLike(Integer value) {
            addCriterion("created_by like", value + "%", "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByLike(Integer value) {
            addCriterion("created_by like", "%" + value + "%", "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByNotLeftLike(Integer value) {
            addCriterion("created_by not like", "%" + value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByNotRightLike(Integer value) {
            addCriterion("created_by not like", value + "%", "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByNotLike(Integer value) {
            addCriterion("created_by not like", "%" + value + "%", "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByIn(List<Integer> values) {
            addCriterion("created_by in", values, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByNotIn(List<Integer> values) {
            addCriterion("created_by not in", values, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByBetween(Integer value1, Integer value2) {
            addCriterion("created_by between", value1, value2, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByNotBetween(Integer value1, Integer value2) {
            addCriterion("created_by not between", value1, value2, "createdBy");
            return (Criteria) this;
        }


        public Criteria andCreatedTimeIsNull() {
            addCriterion("created_time is null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIsNotNull() {
            addCriterion("created_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeEqualTo(Date value) {
            addCriterion("created_time =", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotEqualTo(Date value) {
            addCriterion("created_time <>", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThan(Date value) {
            addCriterion("created_time >", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("created_time >=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThan(Date value) {
            addCriterion("created_time <", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThanOrEqualTo(Date value) {
            addCriterion("created_time <=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLeftLike(Date value) {
            addCriterion("created_time like", "%" + value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeRightLike(Date value) {
            addCriterion("created_time like", value + "%", "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLike(Date value) {
            addCriterion("created_time like", "%" + value + "%", "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotLeftLike(Date value) {
            addCriterion("created_time not like", "%" + value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotRightLike(Date value) {
            addCriterion("created_time not like", value + "%", "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotLike(Date value) {
            addCriterion("created_time not like", "%" + value + "%", "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIn(List<Date> values) {
            addCriterion("created_time in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotIn(List<Date> values) {
            addCriterion("created_time not in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeBetween(Date value1, Date value2) {
            addCriterion("created_time between", value1, value2, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotBetween(Date value1, Date value2) {
            addCriterion("created_time not between", value1, value2, "createdTime");
            return (Criteria) this;
        }


        public Criteria andUpdatedByIsNull() {
            addCriterion("updated_by is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedByIsNotNull() {
            addCriterion("updated_by is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedByEqualTo(Integer value) {
            addCriterion("updated_by =", value, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByNotEqualTo(Integer value) {
            addCriterion("updated_by <>", value, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByGreaterThan(Integer value) {
            addCriterion("updated_by >", value, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByGreaterThanOrEqualTo(Integer value) {
            addCriterion("updated_by >=", value, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByLessThan(Integer value) {
            addCriterion("updated_by <", value, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByLessThanOrEqualTo(Integer value) {
            addCriterion("updated_by <=", value, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByLeftLike(Integer value) {
            addCriterion("updated_by like", "%" + value, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByRightLike(Integer value) {
            addCriterion("updated_by like", value + "%", "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByLike(Integer value) {
            addCriterion("updated_by like", "%" + value + "%", "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByNotLeftLike(Integer value) {
            addCriterion("updated_by not like", "%" + value, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByNotRightLike(Integer value) {
            addCriterion("updated_by not like", value + "%", "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByNotLike(Integer value) {
            addCriterion("updated_by not like", "%" + value + "%", "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByIn(List<Integer> values) {
            addCriterion("updated_by in", values, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByNotIn(List<Integer> values) {
            addCriterion("updated_by not in", values, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByBetween(Integer value1, Integer value2) {
            addCriterion("updated_by between", value1, value2, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByNotBetween(Integer value1, Integer value2) {
            addCriterion("updated_by not between", value1, value2, "updatedBy");
            return (Criteria) this;
        }


        public Criteria andUpdatedTimeIsNull() {
            addCriterion("updated_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIsNotNull() {
            addCriterion("updated_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeEqualTo(Date value) {
            addCriterion("updated_time =", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotEqualTo(Date value) {
            addCriterion("updated_time <>", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeGreaterThan(Date value) {
            addCriterion("updated_time >", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updated_time >=", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeLessThan(Date value) {
            addCriterion("updated_time <", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeLessThanOrEqualTo(Date value) {
            addCriterion("updated_time <=", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeLeftLike(Date value) {
            addCriterion("updated_time like", "%" + value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeRightLike(Date value) {
            addCriterion("updated_time like", value + "%", "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeLike(Date value) {
            addCriterion("updated_time like", "%" + value + "%", "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotLeftLike(Date value) {
            addCriterion("updated_time not like", "%" + value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotRightLike(Date value) {
            addCriterion("updated_time not like", value + "%", "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotLike(Date value) {
            addCriterion("updated_time not like", "%" + value + "%", "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIn(List<Date> values) {
            addCriterion("updated_time in", values, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotIn(List<Date> values) {
            addCriterion("updated_time not in", values, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeBetween(Date value1, Date value2) {
            addCriterion("updated_time between", value1, value2, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotBetween(Date value1, Date value2) {
            addCriterion("updated_time not between", value1, value2, "updatedTime");
            return (Criteria) this;
        }


        public Criteria andDeleteIsNull() {
            addCriterion("is_delete is null");
            return (Criteria) this;
        }

        public Criteria andDeleteIsNotNull() {
            addCriterion("is_delete is not null");
            return (Criteria) this;
        }

        public Criteria andDeleteEqualTo(Integer value) {
            addCriterion("is_delete =", value, "delete");
            return (Criteria) this;
        }

        public Criteria andDeleteNotEqualTo(Integer value) {
            addCriterion("is_delete <>", value, "delete");
            return (Criteria) this;
        }

        public Criteria andDeleteGreaterThan(Integer value) {
            addCriterion("is_delete >", value, "delete");
            return (Criteria) this;
        }

        public Criteria andDeleteGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_delete >=", value, "delete");
            return (Criteria) this;
        }

        public Criteria andDeleteLessThan(Integer value) {
            addCriterion("is_delete <", value, "delete");
            return (Criteria) this;
        }

        public Criteria andDeleteLessThanOrEqualTo(Integer value) {
            addCriterion("is_delete <=", value, "delete");
            return (Criteria) this;
        }

        public Criteria andDeleteLeftLike(Integer value) {
            addCriterion("is_delete like", "%" + value, "delete");
            return (Criteria) this;
        }

        public Criteria andDeleteRightLike(Integer value) {
            addCriterion("is_delete like", value + "%", "delete");
            return (Criteria) this;
        }

        public Criteria andDeleteLike(Integer value) {
            addCriterion("is_delete like", "%" + value + "%", "delete");
            return (Criteria) this;
        }

        public Criteria andDeleteNotLeftLike(Integer value) {
            addCriterion("is_delete not like", "%" + value, "delete");
            return (Criteria) this;
        }

        public Criteria andDeleteNotRightLike(Integer value) {
            addCriterion("is_delete not like", value + "%", "delete");
            return (Criteria) this;
        }

        public Criteria andDeleteNotLike(Integer value) {
            addCriterion("is_delete not like", "%" + value + "%", "delete");
            return (Criteria) this;
        }

        public Criteria andDeleteIn(List<Integer> values) {
            addCriterion("is_delete in", values, "delete");
            return (Criteria) this;
        }

        public Criteria andDeleteNotIn(List<Integer> values) {
            addCriterion("is_delete not in", values, "delete");
            return (Criteria) this;
        }

        public Criteria andDeleteBetween(Integer value1, Integer value2) {
            addCriterion("is_delete between", value1, value2, "delete");
            return (Criteria) this;
        }

        public Criteria andDeleteNotBetween(Integer value1, Integer value2) {
            addCriterion("is_delete not between", value1, value2, "delete");
            return (Criteria) this;
        }

           }

    public static class Criteria extends GeneratedCriteria {

        Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
