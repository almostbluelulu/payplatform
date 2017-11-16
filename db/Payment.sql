-- auto Generated on 2017-09-19 10:41:34 
-- DROP TABLE IF EXISTS `we_payment`; 
CREATE TABLE `wb_payment`(
    `id` VARCHAR (50) NOT NULL  COMMENT 'id',
    `version` BIGINT (15) NOT NULL DEFAULT 0 COMMENT '版本号',
    `yeepay_order_id` VARCHAR (50)  DEFAULT '' COMMENT 'yeepayOrderId',
    `order_id` VARCHAR (50)  DEFAULT '' COMMENT 'order_id',
    `customer_number` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'customerNumber',
    `pay_status` VARCHAR (30)  DEFAULT '' COMMENT 'payStatus',
    `trx_type` VARCHAR(50)  DEFAULT '' COMMENT 'trxType',
    `pay_type` VARCHAR (30)  DEFAULT '' COMMENT 'payType',
    `trx_amt` DECIMAL (12,2) NOT NULL DEFAULT 0.00 COMMENT 'trxAmt',
    `real_amount` DECIMAL (12,2)  DEFAULT 0.00 COMMENT 'realAmount',
    `fee_amount` DECIMAL (12,2)  DEFAULT 0.00 COMMENT 'feeAmount',
    `bank_name` VARCHAR (128)  DEFAULT '' COMMENT 'bankName',
    `card_number` VARCHAR (50)  DEFAULT '' COMMENT 'cardNumber',
    `card_type` VARCHAR (30)  DEFAULT '' COMMENT 'cardType',
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT 'createTime',
    `complete_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT 'completeTime',
    `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX(id),
    INDEX(yeepay_order_id),
    INDEX(order_id),
    INDEX(create_time),
    INDEX(complete_time),
    INDEX(update_time),
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '`we_payment`';
