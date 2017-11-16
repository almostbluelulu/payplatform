CREATE TABLE `wb_push_msg` (
  `message_no`     VARCHAR(50) UNIQUE NOT NULL DEFAULT ''
  COMMENT 'messageNo',
  `jpush_id`       VARCHAR(50)        NOT NULL DEFAULT ''
  COMMENT 'jpushId',
  `operator`       VARCHAR(50)        NOT NULL DEFAULT ''
  COMMENT 'operator',
  `type`           VARCHAR(50)        NOT NULL DEFAULT ''
  COMMENT 'type',
  `title`          VARCHAR(50)        NOT NULL DEFAULT ''
  COMMENT 'title',
  `content`        VARCHAR(50)        NOT NULL DEFAULT ''
  COMMENT 'content',
  `life_start`     DATETIME           NOT NULL DEFAULT '1000-01-01 00:00:00'
  COMMENT 'lifeStart',
  `life_end`       DATETIME           NOT NULL DEFAULT '1000-01-01 00:00:00'
  COMMENT 'lifeEnd',
  `url1`           VARCHAR(50)        NOT NULL DEFAULT ''
  COMMENT 'url1',
  `url2`           VARCHAR(50)        NOT NULL DEFAULT ''
  COMMENT 'url2',
  `user_numbers`   VARCHAR(64)        NOT NULL DEFAULT ''
  COMMENT 'userNumbers',
  `app_version_id` VARCHAR(50)        NOT NULL DEFAULT ''
  COMMENT 'appVersionId',
  `platform_enum`  VARCHAR(50)        NOT NULL DEFAULT ''
  COMMENT 'platformEnum',
  `manufacturer`   VARCHAR(50)        NOT NULL DEFAULT ''
  COMMENT 'manufacturer',
  `model`          VARCHAR(50)        NOT NULL DEFAULT ''
  COMMENT 'model',
  `push_status`    VARCHAR(50)        NOT NULL DEFAULT ''
  COMMENT 'pushStatus',
  `push_type`      VARCHAR(50)        NOT NULL DEFAULT ''
  COMMENT 'pushType',
  `push_time`      TIMESTAMP          NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT 'pushTime',
  `id`             BIGINT(15)         NOT NULL AUTO_INCREMENT
  COMMENT 'id',
  `create_time`    TIMESTAMP          NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT 'createTime',
  `update_time`    TIMESTAMP          NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT 'updateTime',
  INDEX (message_no),
  INDEX (user_numbers),
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT '`wb_push_msg`';
INSERT INTO wb_sequence VALUES ('seq_wb_push_msg', '0', '1');
SELECT nextval('seq_wb_push_msg');