-- auto Generated on 2017-09-25 17:33:28 
-- DROP TABLE IF EXISTS `wb_user2private_key`; 
CREATE TABLE `wb_user2private_key`(
    `user_number` VARCHAR (50) NOT NULL COMMENT 'userNumber',
    `customer_number` VARCHAR (50) NOT NULL COMMENT 'customerNumber',
    `private_key` VARCHAR (1000) NOT NULL COMMENT 'privateKey',
    `id` BIGINT (15) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'createTime',
    `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'updateTime',
    INDEX(customer_number),
    INDEX(user_number),
    INDEX(create_time),
    INDEX(update_time),
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '`wb_user2private_key`';
