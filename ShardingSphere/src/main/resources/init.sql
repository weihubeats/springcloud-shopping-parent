DROP TABLE IF EXISTS `t_order_0000`;
CREATE TABLE `t_order_0000` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`user_id` bigint(20) DEFAULT NULL,
`order_id` bigint(20) DEFAULT NULL,
`user_name` varchar(255) DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1239 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for t_order_0001
-- ----------------------------
DROP TABLE IF EXISTS `t_order_0001`;
CREATE TABLE `t_order_0001` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`user_id` bigint(20) DEFAULT NULL,
`order_id` bigint(20) DEFAULT NULL,
`user_name` varchar(255) DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1239 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `order_config`;
CREATE TABLE `order_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `pay_timeout` int(11) DEFAULT NULL COMMENT '支付超时时间;单位：分钟',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='订单配置表';