CREATE TABLE `wb_settle` (
  `id`              BIGINT(15)     NOT NULL AUTO_INCREMENT
  COMMENT 'id',
  `customer_number` VARCHAR(50)    NOT NULL
  COMMENT 'customerNumber',
  `settle_amount`   DECIMAL(12, 2) NOT NULL DEFAULT 0
  COMMENT 'settleAmount',
  `settle_fee`      DECIMAL(12, 2) COMMENT 'settleFee',
  `settle_type`     VARCHAR(50)    NOT NULL
  COMMENT 'settleType',
  `settle_time`     DATE           NOT NULL
  COMMENT 'settleDate',
  `create_time`     TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT 'createTime',
  `update_time`     TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT 'updateTime',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT '`wb_settle`';