/*
 * Copyright (c) 2020-2030 Sishun.Co.Ltd. All Rights Reserved.
 */
package com.vevor.tools.seo.robot.pojo.vo;

import lombok.Data;

/**
 * @Description ：更新 Robot.txt 所需属性VO对象
 * @Program ：vevor-tools
 * @Author ：Li Hui
 * @Date ：Created in 2020/10/29 19:42
 * @Version ：1.0.1
 */
@Data
public class RobotInfoVO {

    /**
     * robot配置表中content内容与sitemap表中content内容拼接后的内容
     */
    private String content;

}
