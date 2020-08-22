create database db0;
create database db1;
use db0;
drop table if exists t_user;
CREATE TABLE `t_user` (
  `id` int(11) PRIMARY key auto_increment COMMENT '用户编号',
  `user_name` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '账号',
  UNIQUE KEY `idx_user_name` (`user_name`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

insert into t_user(user_name) values('阿离');
insert into t_user(user_name) values('奏');
insert into t_user(user_name) values('阿花 ');


use db1;
drop table if exists t_order;
CREATE TABLE `t_order` (
  `id` int(11) PRIMARY key auto_increment COMMENT '订单编号',
  `user_id` int(16) DEFAULT NULL COMMENT '用户编号'

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='订单表';

insert into t_order(user_id) values(123);
insert into t_order(user_id) values(45);
insert into t_order(user_id) values(789);

