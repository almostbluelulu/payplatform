-- auto Generated on 2017-09-18 19:00:03 
-- DROP TABLE IF EXISTS `wb_qr_code`; 
CREATE TABLE `wb_qr_code`(
    `id` BIGINT (15)  AUTO_INCREMENT COMMENT 'id',
    `qr_id` VARCHAR (50) NOT NULL  DEFAULT '' COMMENT 'qrId',
    `qr_url` VARCHAR (128) NOT NULL  DEFAULT '' COMMENT 'qrUrl',
    `customer_number` VARCHAR (50)  DEFAULT '' COMMENT 'customerNumber',
    `shop_number` VARCHAR (50)  DEFAULT '' COMMENT 'shopNumber',
    `user_number` VARCHAR (50)  DEFAULT '' COMMENT 'userNumber',
    `remark` VARCHAR (128)  DEFAULT '' COMMENT 'remark',
    `status` VARCHAR (32) NOT NULL DEFAULT '' COMMENT 'status',
    `relation_id` VARCHAR (50)  DEFAULT '' COMMENT 'relationId',
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT 'createTime',
    `update_time` TIMESTAMP  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'updateTime',
    INDEX(qr_id),
    INDEX(customer_number),
    INDEX(shop_number),
    INDEX(user_number),
    INDEX(relation_id),
    INDEX(create_time),
    INDEX(update_time),
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '`wb_qr_code`';
INSERT INTO wb_sequence VALUES ('seq_wb_qrcode', '1', '1');
