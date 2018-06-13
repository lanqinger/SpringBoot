--采用SpringBoot、MyBatis框架
--使用Spring Boot热加载
--访问路径：http://127.0.0.1:8081/user/getUserList.do
建表语句：
CREATE TABLE `user_info` (
`id`  int(11) NOT NULL ,
account_number VARCHAR(50) NOT NULL ,
password VARCHAR(50) NOT NULL ,
create_date VARCHAR(16),
PRIMARY KEY (`id`)
)
;

--使用Swagger2访问路径：http://127.0.0.1:8081/swagger-ui.html

