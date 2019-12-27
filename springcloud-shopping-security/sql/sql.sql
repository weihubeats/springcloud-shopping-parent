/*
MySQL Backup
Database: security
Backup Time: 2019-12-26 19:23:22
*/

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `security`.`sys_permission`;
DROP TABLE IF EXISTS `security`.`sys_role`;
DROP TABLE IF EXISTS `security`.`sys_role_permission`;
DROP TABLE IF EXISTS `security`.`sys_user`;
DROP TABLE IF EXISTS `security`.`sys_user_role`;
CREATE TABLE `sys_permission` (
  `id` int(10) NOT NULL COMMENT '主键',
  `permName` varchar(50) DEFAULT NULL,
  `permTag` varchar(50) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL COMMENT '请求url',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';
CREATE TABLE `sys_role` (
  `id` int(10) NOT NULL COMMENT '主键',
  `roleName` varchar(50) DEFAULT NULL COMMENT '角色名',
  `roleDesc` varchar(50) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';
CREATE TABLE `sys_role_permission` (
  `role_id` int(10) DEFAULT NULL,
  `perm_id` int(10) DEFAULT NULL,
  KEY `FK_Reference_3` (`role_id`),
  KEY `FK_Reference_4` (`perm_id`),
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`),
  CONSTRAINT `FK_Reference_4` FOREIGN KEY (`perm_id`) REFERENCES `sys_permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限表';
CREATE TABLE `sys_user` (
  `id` int(10) NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `realname` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `createDate` date DEFAULT NULL,
  `lastLoginTime` date DEFAULT NULL,
  `enabled` int(5) DEFAULT NULL,
  `accountNonExpired` int(5) DEFAULT NULL,
  `accountNonLocked` int(5) DEFAULT NULL,
  `credentialsNonExpired` int(5) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';
CREATE TABLE `sys_user_role` (
  `user_id` int(10) DEFAULT NULL,
  `role_id` int(10) DEFAULT NULL,
  KEY `FK_Reference_1` (`user_id`),
  KEY `FK_Reference_2` (`role_id`),
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';
BEGIN;
LOCK TABLES `security`.`sys_permission` WRITE;
DELETE FROM `security`.`sys_permission`;
INSERT INTO `security`.`sys_permission` (`id`,`permName`,`permTag`,`url`) VALUES (1, '查询订单', 'showOrder', '/showOrder'),(2, '添加订单', 'addOrder', '/addOrder'),(3, '修改订单', 'updateOrder', '/updateOrder'),(4, '删除订单', 'deleteOrder', '/deleteOrder');
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `security`.`sys_role` WRITE;
DELETE FROM `security`.`sys_role`;
INSERT INTO `security`.`sys_role` (`id`,`roleName`,`roleDesc`) VALUES (1, 'admin', '管理员'),(2, 'user', '普通用户');
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `security`.`sys_role_permission` WRITE;
DELETE FROM `security`.`sys_role_permission`;
INSERT INTO `security`.`sys_role_permission` (`role_id`,`perm_id`) VALUES (1, 1),(1, 2),(1, 3),(1, 4),(2, 1),(2, 2);
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `security`.`sys_user` WRITE;
DELETE FROM `security`.`sys_user`;
INSERT INTO `security`.`sys_user` (`id`,`username`,`realname`,`password`,`createDate`,`lastLoginTime`,`enabled`,`accountNonExpired`,`accountNonLocked`,`credentialsNonExpired`) VALUES (1, 'admin', '阿离', '5ab724e6a167854b481cee5b711cb346\r\n', '2019-12-26', '2019-12-26', 1, 1, 1, 1),(2, 'user', '小奏', '5ab724e6a167854b481cee5b711cb346\r\n', '2019-12-26', '2019-12-26', 1, 1, 1, 1);
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `security`.`sys_user_role` WRITE;
DELETE FROM `security`.`sys_user_role`;
INSERT INTO `security`.`sys_user_role` (`user_id`,`role_id`) VALUES (1, 1),(2, 2);
UNLOCK TABLES;
COMMIT;
