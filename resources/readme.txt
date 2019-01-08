#######################################################
#创建表demo_t
CREATE TABLE `test`.`demo_t`
( `id` INT NOT NULL AUTO_INCREMENT, `demo_name` VARCHAR(50), `password` VARCHAR(50), `age` INT, PRIMARY KEY (`id`) )
 CHARSET=utf8; 
#插入数据
INSERT INTO `test`.`demo_t` 
(`id`, `demo_name`, `password`, `age`) 
VALUES ('1', '张三', '7788250', '25');


#######################################################
#v1.5版本-->拦截器参考https://blog.csdn.net/qq_30745307/article/details/80974407


#######################################################
#创建表t_user
CREATE TABLE `test`.`t_user`
( `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '用户id', 
`username` VARCHAR(50) COMMENT '用户名', 
`password` VARCHAR(50) COMMENT '密码', 
`uuid` VARCHAR(50) COMMENT '盐', 
`gender` INT COMMENT '性别 0：男，1：女', 
`phone` VARCHAR(50) COMMENT '移动电话', 
`email` VARCHAR(50) COMMENT '邮箱', 
`createdUser` VARCHAR(50) COMMENT '创建人', 
`createdTime` DATE COMMENT '创建时间', 
`modifiedUser` VARCHAR(50) COMMENT '修改人', 
`modifiedTime` DATE COMMENT '修改时间', PRIMARY KEY (`id`) ) ENGINE=INNODB CHARSET=utf8; 

ALTER TABLE `test`.`t_user` ADD COLUMN `realname` VARCHAR(50) NULL COMMENT '真实姓名' AFTER `modifiedTime`;

#####################################################
CREATE TABLE `test`.`t_userFile`( `id` BIGINT NOT NULL AUTO_INCREMENT, `fileName` VARCHAR(50), `filePath` VARCHAR(100), `fileType` VARCHAR(50), `filePurposes` INT DEFAULT 0, `fileStatus` INT DEFAULT 0, `userId` BIGINT, `createdUser` VARCHAR(50), `createdTime` DATE, `modifiedUser` VARCHAR(50), `modifiedTime` DATE, PRIMARY KEY (`id`) ); 


#######################################################
配置ftp,详见
https://blog.csdn.net/qq_43566496/article/details/85336010






























