/*
 * Copyright (c) 2020-2030 Sishun.Co.Ltd. All Rights Reserved.
 */
package com.vevor.tools.seo.untils;

import com.jcraft.jsch.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description ：登录服务器
 * @Program ：vevor-tools
 * @Author ：Li Hui
 * @Date ：Created in 2020/10/21 16:02
 * @Version ：1.0.1
 */
@Slf4j
@Data
public class LoginSftpServer {
    /**
     * ChannelSftp
     */
    private ChannelSftp sftp;
    /**
     * Session
     */
    private Session session;

    /**
     * 登录服务器创建ftp连接
     *
     * @param name  用户名
     * @param ip    ip地址
     * @param port  端口号
     * @param privateKey 私钥存储地址
     * @return ChannelSftp ftp通道
     */
    public ChannelSftp login(String name,String ip,int port,String privateKey){
        JSch jsch = new JSch();
        try{
            if(privateKey != null){
                //设置登陆主机的秘钥
                jsch.addIdentity(privateKey);
            }
            //采用指定的端口连接服务器
            session = jsch.getSession(name, ip, port);
            session.setConfig("StrictHostKeyChecking","no");
            session.connect();
            //创建sftp通信通道
            Channel channel = session.openChannel("sftp");
            channel.connect();
            sftp = (ChannelSftp) channel;
            log.info("sftp server connect success !!");
        }catch (JSchException e){
            log.error("SFTP服务器连接异常！！", e);
        }
        return sftp;
    }

    /**
     * 关闭SFTP连接
     *
     */
    public void logout(){
        if(sftp != null){
            if(sftp.isConnected()){
                sftp.disconnect();
                log.info("sftp is close already");
            }
        }
        if(session != null){
            if(session.isConnected()){
                session.disconnect();
                log.info("session is close already");
            }
        }
    }
}
