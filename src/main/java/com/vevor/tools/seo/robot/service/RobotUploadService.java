/*
 * Copyright (c) 2020-2030 Sishun.Co.Ltd. All Rights Reserved.
 */
package com.vevor.tools.seo.robot.service;

import com.vevor.tools.seo.robot.pojo.vo.RobotInfoVO;

/**
 * @Description ：服务器Robots.txt文件上传
 * @Program ：vevor-tools-seo
 * @Author ：Li Hui
 * @Date ：Created in 2020/10/26 11:56
 * @Version ：1.0.1
 */
public interface RobotUploadService {

    /**
     *以String字符的方式上传robot内容
     *
     * @param robotInfoVO 上传文件所需参数对象->存储内容
     * @return boolean 上传结果
     */
    boolean uploadRobot(RobotInfoVO robotInfoVO);

    /**
     *以文本的方式上传robot内容
     *
     * @param robotInfoVO 上传文件所需参数对象->存储内容
     * @return boolean 上传结果
     */
    boolean uploadRobotByScp(RobotInfoVO robotInfoVO) ;
}
