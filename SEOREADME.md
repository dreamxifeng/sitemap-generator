# Getting Started
### SEO优化
运行环境:   jdk,mysql,此项目利用consul配置全局配置类,
    *-可选择在application.xml/yml配置sitemap对象参数
    | 维护注意事项:
    |1. 版本升级必须要兼容低版本, 过期的内容使用注解标识出来;
    | 2. 更新内容必须升级版本号;
    |3. 项目的打包类型为pom, 该项目内部可以分别创建公共module, 每个单独的module的打包方式都应该是jar, 每个module都应该按照“低耦合,高内聚”的原则进行开发
    *
**Seo优化模块注意事项:**
1.全局参数配置类: FileUploadConfiguration   配置方式读取属性值

        配置文件参考:    sitemap 与 spring 属性配置同级
        1.consul注册中心内 用于全局consul配置中心进行统一配置   
        ------------------------------------------- 
        |   sitemap:                              |
        |     ip: 127.0.0.1                       |
        |     username: root                      |
        |     password: root                      |
        |     ......                              |
        |   spring:                               |
        |     ......                              |
        -------------------------------------------   
        2.application.xml全局配置:
        ------------------------------------------=
        | sitemap.ip=127.0.0.1                    |
        | sitemap.username=root                   |
        | sitemap.password=root                   |
        | ......                                  |
        |#port                                    | 
        |server.port=8080                         |
        | ##                                      |
        | spring.datasource.url=jdbc:mysql:.....  |
        -------------------------------------------  
    
**2.sitemap.xml生成源于开源工具** https://github.com/dfabulich/sitemapgen4j/releases
       （此模块进行自定义重写另外业务若需重写,所有类修饰符已为public,模仿SitemapGenerator与SitemapIndexUrl 修改即可）
        sitemap.xml 生成须知: 
        -------------------------------------------------------------------------------------
        |1.非产品化的url 即正常url
        |2.产品化的url(带图片地址)
        |     格式为 ："http://www.vevor.com/list-name.html21,http://www.vevor.com/list.jpg"
        |     前者为页面的地址，后者为商品封面的url。中间以','号隔开。
        所有url  域名都必须相同，否则会校验失败导致sitemap.xml生成失败{包括图片的绝对网址}
                    即:  ”http://www.vevor.com“  相同 。其他域名同理
        -------------------------------------------------------------------------------------     
        
**3.FileUploadConfiguration.privateKey 本地私钥:SSH非对称加密 ->**  
        需服务器开启ssh:
        +——————————————————————————+——————————————————————————+——————————————————————————————————-—--——+
        | 确定虚拟机已经安装openssh-server,在终端输入yum list installed | grep openssh-server                |
        | 如没有安装使用命令进行安装：                                                                       |
        | yum install openssh-server                                                                   |
        | 对sshd服务进行配置，配置文件的位置在 /etc/ssh/sshd_config, 设置中用到的关键参数前的#号去掉并做相应的修改即可： |
        | Port  #监听端口 默认22                                                                          |
        | ListenAddress # 监听地址                                                                       |
        | PermitRootLogin #运行远程登录                                                                   |
        | MaxSessions  #最大的连接session数 默认10                                                         |               
        | PasswordAuthentication  # 用户密码作为验证链接                                                    |                 
        | 重启服务：                                                                                      |
        | service sshd restart                                                                          |
        +——————————————————————————+——————————————————————————+——————————————————————————————————----———+
                过程太多了==！
        tip:推荐文章案例说明: 仅为学习参考 推荐博客：图解SSH原理 
            作者：TopGun_Viper     來源：简书
            链接：https://www.jianshu.com/p/33461b619d53
                     
**4.sitemap.xml生成:**
        sitemap 文件名: 默认生成 sitemap.xml。
        自定义文件名称由 SiteMapInfoVO.pageTypeName属性替换
        url.length> 50000时 自动生成多文件 例如:sitemap1.xml,sitemap2.xml....以此类推
        索引sitemap文件: 默认生成 sitemap_index.xml 
        存放路径为consul自主配置值，属性请查看 ‘ FileUploadConfiguration ’
        全局sitemap地图索引文件为:sitemap_index.xml
        tip：<xhtml:link rel="alternate" hreflang="en-au" href="https://www.vevor.com/list-name.html"/></url>
        标签内 hreflang 为站点或服务器域名 或国家所使用的语言:  格式自主配置: 
        推荐 "{站点所用语种}-{站点国家简写}"  例如:  "en-au" 表明:英语-欧洲
        
**5.robots.txt生成:**
        以字符串方式直接写入 服务器自主配置路径下 ；保存为自主配置:   文件名.txt    ；  
        数据库文本内容格式:保存回车，Tab,空格等可见字符
        前端text内值，js保存格式即可。显示视情况对值进行转换。回车->   <br>
        相关可见字符js转换方式可参考文章:
            https://blog.csdn.net/u012606532/article/details/77802535
            
**6.ScpUtils 类:**
        提供scp文件上传，文件解压。     
                  
-------------------------------------------------------------------------------------------        
 
