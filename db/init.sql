DROP TABLE IF EXISTS `wb_sequence`;
CREATE TABLE wb_sequence
(
  seq_name      VARCHAR(50) PRIMARY KEY NOT NULL,
  current_val   BIGINT(20)              NOT NULL,
  increment_val INT(11) DEFAULT '1'     NOT NULL
);
CREATE UNIQUE INDEX wb_sequence_seq_name_uindex
  ON wb_sequence (seq_name);

DROP FUNCTION IF EXISTS `currval`;
CREATE FUNCTION currval(v_seq_name VARCHAR(50))
  RETURNS BIGINT
  BEGIN
    DECLARE val BIGINT;
    SET val = 0;
    SELECT current_val
    INTO val
    FROM wb_sequence
    WHERE seq_name = v_seq_name;
    RETURN val;
  END;

DROP FUNCTION IF EXISTS `nextval`;
CREATE FUNCTION nextval(v_seq_name VARCHAR(50))
  RETURNS BIGINT
  BEGIN
    UPDATE wb_sequence
    SET current_val = current_val + increment_val
    WHERE seq_name = v_seq_name;
    RETURN currval(v_seq_name);
  END;

#用户表
DROP TABLE IF EXISTS `wb_user`;
CREATE TABLE wb_user
(
  phone_number    VARCHAR(64) DEFAULT ''              NOT NULL
  COMMENT 'phoneNumber',
  user_number     VARCHAR(50) DEFAULT ''              NOT NULL
  COMMENT 'userNumber',
  customer_number VARCHAR(50) COMMENT 'accountId',
  role_id         BIGINT(15) COMMENT 'roleId',
  user_status     VARCHAR(20) DEFAULT ''              NOT NULL
  COMMENT 'userStatus',
  user_pwd        VARCHAR(64) DEFAULT ''
  COMMENT 'userPwd',
  id              BIGINT(15) PRIMARY KEY              NOT NULL
  COMMENT 'id'                AUTO_INCREMENT,
  create_time     TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  COMMENT 'createTime',
  update_time     TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  COMMENT 'updateTime'
);
CREATE INDEX create_time
  ON wb_user (create_time);
CREATE UNIQUE INDEX phone_number
  ON wb_user (phone_number);
CREATE INDEX update_time
  ON wb_user (update_time);
CREATE UNIQUE INDEX user_number
  ON wb_user (user_number);
INSERT INTO wb_sequence VALUES ('seq_wb_user', '1', '1');
# SELECT nextval('seq_wb_user');

#token映射
DROP TABLE IF EXISTS `wb_user_token`;
CREATE TABLE wb_user_token
(
  id           BIGINT(15) PRIMARY KEY              NOT NULL
  COMMENT 'id'              AUTO_INCREMENT,
  phone_number VARCHAR(50) DEFAULT ''              NOT NULL
  COMMENT 'phoneNumber',
  user_number  VARCHAR(50) DEFAULT ''              NOT NULL
  COMMENT 'userNumber',
  token        VARCHAR(254) DEFAULT ''
  COMMENT 'token',
  imei         VARCHAR(100) DEFAULT ''             NOT NULL
  COMMENT 'imei',
  refresh_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  COMMENT 'refreshTime',
  expire_time  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  COMMENT 'expireTime'
);
CREATE INDEX token
  ON wb_user_token (token);
CREATE UNIQUE INDEX user_number
  ON wb_user_token (user_number);

#开户表
DROP TABLE IF EXISTS `wb_account_open`;
CREATE TABLE wb_account_open
(
  id                  BIGINT(15) PRIMARY KEY              NOT NULL
  COMMENT 'id' AUTO_INCREMENT,
  user_number         VARCHAR(50)                         NOT NULL
  COMMENT '用户编号',
  customer_no         VARCHAR(50)                         NOT NULL
  COMMENT '商户编号',
  yp_customer_number  VARCHAR(50) COMMENT '易宝商户编号',
  account_open_status VARCHAR(20)                         NOT NULL
  COMMENT '开户状态',
  company_type        VARCHAR(20)                         NOT NULL
  COMMENT '商户类型',
  legal_name          VARCHAR(128) COMMENT '法人姓名',
  legal_phone_no      VARCHAR(128) COMMENT '法人手机号',
  legal_id_card       VARCHAR(128) COMMENT '法人身份证号',
  mer_full_name       VARCHAR(256) COMMENT '商户全称',
  mer_short_name      VARCHAR(256) COMMENT '商户品牌名称/简称',
  mer_cert_type       VARCHAR(64) COMMENT '证件类型',
  mer_cert_no         VARCHAR(128) COMMENT '营业执照证件号',
  mer_level1_no       VARCHAR(32) COMMENT '商户一级分类编码',
  mer_level2_no       VARCHAR(32) COMMENT '商户二级分类编码',
  mer_province_code   VARCHAR(32) COMMENT '商户经营地址所在省',
  mer_city_code       VARCHAR(32) COMMENT '商户经营地址所在市',
  mer_district_code   VARCHAR(32) COMMENT '商户经营地址所在区',
  mer_address         VARCHAR(512) COMMENT '商户经营地址所在具体地址',
  mer_contact_name    VARCHAR(64) COMMENT '商户联系人姓名',
  mer_contact_phone   VARCHAR(128) COMMENT '商户联系人手机',
  mer_contact_email   VARCHAR(128) COMMENT '商户联系人邮箱',
  tax_regist_cert     VARCHAR(256) COMMENT '税务登记证编号',
  account_license     VARCHAR(64) COMMENT '开户许可证编号',
  org_code            VARCHAR(256) COMMENT '组织机构代码',
  is_org_code_long    VARCHAR(20) COMMENT '组织机构代码证是否长期有效',
  org_code_expiry     DATE COMMENT '组织机构代理证有效期',
  mer_scope           VARCHAR(1024) COMMENT '商户经营范围',
  settle_card_number  VARCHAR(128) COMMENT '结算银行账号或者银行卡号',
  head_bank_code      VARCHAR(256) COMMENT '开户行总行编码',
  bank_code           VARCHAR(256) COMMENT '开户行支行编码',
  bank_province_code  VARCHAR(32) COMMENT '开户省编码',
  bank_city_code      VARCHAR(32) COMMENT '开户市编码',
  product_info        VARCHAR(2048) COMMENT '开通产品',
  file_info           VARCHAR(4000) COMMENT '资质影印件',
  business_function   VARCHAR(1000) COMMENT '业务功能',
  mer_authorize_type  VARCHAR(32) COMMENT '授权类型',
  open_lbs            VARCHAR(50) COMMENT '坐标',
  create_time         TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  COMMENT '创建时间',
  update_time         TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  COMMENT '更新时间',
  mer_province_name   VARCHAR(128),
  mer_city_name       VARCHAR(128),
  mer_district_name   VARCHAR(128),
  head_bank_name      VARCHAR(256)
);
CREATE INDEX create_time
  ON wb_account_open (create_time);
CREATE INDEX customer_no
  ON wb_account_open (customer_no);
CREATE INDEX update_time
  ON wb_account_open (update_time);
CREATE UNIQUE INDEX user_number
  ON wb_account_open (user_number);
CREATE INDEX yp_customer_number
  ON wb_account_open (yp_customer_number);
INSERT INTO wb_sequence VALUES ('seq_wb_account_open', '1', '1');

#订单表
DROP TABLE IF EXISTS `wb_order`;
CREATE TABLE wb_order
(
  id              VARCHAR(50) PRIMARY KEY             NOT NULL
  COMMENT '系统唯一流水号',
  version         BIGINT(15) DEFAULT '0'              NOT NULL
  COMMENT '版本号',
  customer_number VARCHAR(50) DEFAULT ''              NOT NULL
  COMMENT 'customerNumber',
  request_no      VARCHAR(64)    DEFAULT ''
  COMMENT '商户订单号',
  yeepay_order_id VARCHAR(50)    DEFAULT ''
  COMMENT '易宝订单号',
  user_number     VARCHAR(50)    DEFAULT ''
  COMMENT 'userNumber',
  qr_id           VARCHAR(50)    DEFAULT ''
  COMMENT '二维码id',
  token           VARCHAR(128)   DEFAULT ''
  COMMENT '订单token',
  pay_type        VARCHAR(50)    DEFAULT ''
  COMMENT '支付类型',
  order_type      VARCHAR(50)    DEFAULT ''
  COMMENT '订单类型',
  scan_type       VARCHAR(50)    DEFAULT ''
  COMMENT 'scanType',
  order_status    VARCHAR(50)    DEFAULT ''
  COMMENT 'orderStatus',
  trx_amt         DECIMAL(12, 2) DEFAULT '0.00'
  COMMENT 'trxAmt',
  real_amount     DECIMAL(12, 2) DEFAULT '0.00'
  COMMENT 'realAmount',
  fee_amount      DECIMAL(12, 2) DEFAULT '0.00'
  COMMENT 'feeAmount',
  refund_amount   DECIMAL(12, 2) DEFAULT '0.00'
  COMMENT 'refundAmount',
  create_time     TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  COMMENT 'createTime',
  complete_time   TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  COMMENT 'completeTime',
  update_time     TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  COMMENT 'updateTime',
  err_code        VARCHAR(20)    DEFAULT ''
  COMMENT '错误码',
  err_message     VARCHAR(128)   DEFAULT ''
  COMMENT '错误信息',
  remark          VARCHAR(128)   DEFAULT ''
  COMMENT 'remark'
);
CREATE INDEX complete_time
  ON wb_order (complete_time);
CREATE INDEX create_time
  ON wb_order (create_time);
CREATE INDEX id
  ON wb_order (id);
CREATE INDEX request_no
  ON wb_order (request_no);
CREATE INDEX token
  ON wb_order (token);
CREATE INDEX update_time
  ON wb_order (update_time);
CREATE INDEX user_number
  ON wb_order (user_number);
CREATE INDEX yeepay_order_id
  ON wb_order (yeepay_order_id);
INSERT INTO wb_sequence VALUES ('seq_wb_order', '1', '1');


#支付记录
DROP TABLE IF EXISTS `wb_payment`;
CREATE TABLE wb_payment
(
  id              VARCHAR(50) PRIMARY KEY             NOT NULL
  COMMENT 'id',
  version         BIGINT(15) DEFAULT '0'              NOT NULL
  COMMENT '版本号',
  yeepay_order_id VARCHAR(50)    DEFAULT ''
  COMMENT 'yeepayOrderId',
  order_id        VARCHAR(50)    DEFAULT ''
  COMMENT 'order_id',
  customer_number VARCHAR(50) DEFAULT ''              NOT NULL
  COMMENT 'customerNumber',
  pay_status      VARCHAR(30)    DEFAULT ''
  COMMENT 'payStatus',
  trx_type        VARCHAR(50)    DEFAULT ''
  COMMENT 'trxType',
  pay_type        VARCHAR(30)    DEFAULT ''
  COMMENT 'payType',
  trx_amt         DECIMAL(12, 2) DEFAULT '0.00'       NOT NULL
  COMMENT 'trxAmt',
  real_amount     DECIMAL(12, 2) DEFAULT '0.00'
  COMMENT 'realAmount',
  fee_amount      DECIMAL(12, 2) DEFAULT '0.00'
  COMMENT 'feeAmount',
  bank_name       VARCHAR(128)   DEFAULT ''
  COMMENT 'bankName',
  card_number     VARCHAR(50)    DEFAULT ''
  COMMENT 'cardNumber',
  card_type       VARCHAR(30)    DEFAULT ''
  COMMENT 'cardType',
  create_time     TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  COMMENT 'createTime',
  complete_time   TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  COMMENT 'completeTime',
  update_time     TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  COMMENT '更新时间'
);
CREATE INDEX complete_time
  ON wb_payment (complete_time);
CREATE INDEX create_time
  ON wb_payment (create_time);
CREATE INDEX id
  ON wb_payment (id);
CREATE INDEX order_id
  ON wb_payment (order_id);
CREATE INDEX update_time
  ON wb_payment (update_time);
CREATE INDEX yeepay_order_id
  ON wb_payment (yeepay_order_id);
INSERT INTO wb_sequence VALUES ('seq_wb_payment', '1', '1');

#退款订单
# DROP TABLE IF EXISTS `wb_refund`;
# CREATE TABLE IF NOT EXISTS `wb_refund` (
#   `id`               VARCHAR(50),
#   `request_no`       VARCHAR(50),
#   `order_id`         VARCHAR(50) NOT NULL, #原订单号
#   `payment_id`       VARCHAR(50) NOT NULL, #原付款单ID
#   `yeepay_refund_id` VARCHAR(50),
#   `refund_status`    VARCHAR(50) NOT NULL, #退款状态
#   `trx_amount`       DECIMAL(12, 2), #原订单金额
#   `real_pay`         DECIMAL(12, 2), #原支付金额
#   `refund_amount`    DECIMAL(12, 2), #发起退款金额
#   `real_refund`      DECIMAL(12, 2), #实际退款金额
#   `fee_amount`       DECIMAL(12, 2), #手续费
#   `error_code`       VARCHAR(20),
#   `error_msg`        VARCHAR(50),
#   `create_time`      TIMESTAMP, #发起时间
#   `update_time`      TIMESTAMP,
#   `complete_time`    TIMESTAMP, #完成时间
#   PRIMARY KEY (`id`)
# )
#   ENGINE = InnoDB
#   DEFAULT CHARSET = utf8;

#结算表
DROP TABLE IF EXISTS `wb_settle`;
CREATE TABLE wb_settle
(
  id              BIGINT(15) PRIMARY KEY              NOT NULL
  COMMENT 'id' AUTO_INCREMENT,
  customer_number VARCHAR(50)                         NOT NULL
  COMMENT 'customerNumber',
  settle_amount   DECIMAL(12, 2) DEFAULT '0.00'       NOT NULL
  COMMENT 'settleAmount',
  settle_fee      DECIMAL(12, 2) COMMENT 'settleFee',
  settle_type     VARCHAR(50)                         NOT NULL
  COMMENT 'settleType',
  settle_time     DATE                                NOT NULL
  COMMENT 'settleDate',
  create_time     TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  COMMENT 'createTime',
  update_time     TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  COMMENT 'updateTime'
);

#推送消息表
DROP TABLE IF EXISTS `wb_push_msg`;
CREATE TABLE wb_push_msg
(
  message_no     VARCHAR(50)                         NOT NULL
  COMMENT 'messageNo',
  jpush_id       VARCHAR(50) COMMENT 'jpushId',
  operator       VARCHAR(50)                         NOT NULL
  COMMENT 'operator',
  type           VARCHAR(50)                         NOT NULL
  COMMENT 'type',
  title          VARCHAR(50)                         NOT NULL
  COMMENT 'title',
  content        VARCHAR(512) DEFAULT ''             NOT NULL
  COMMENT 'content',
  life_start     TIMESTAMP,
  life_end       TIMESTAMP,
  url1           VARCHAR(50) COMMENT 'url1',
  url2           VARCHAR(50) COMMENT 'url2',
  app_version_id VARCHAR(50) COMMENT 'appVersionId',
  platform_enum  VARCHAR(50) COMMENT 'platformEnum',
  manufacturer   VARCHAR(50) COMMENT 'manufacturer',
  model          VARCHAR(50) COMMENT 'model',
  push_status    VARCHAR(50)                         NOT NULL
  COMMENT 'pushStatus',
  push_type      VARCHAR(50) COMMENT 'pushType',
  push_time      TIMESTAMP COMMENT 'pushTime',
  id             BIGINT(15) PRIMARY KEY              NOT NULL
  COMMENT 'id' AUTO_INCREMENT,
  create_time    TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  COMMENT 'createTime',
  update_time    TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  COMMENT 'updateTime',
  user_numbers   VARCHAR(64)                         NOT NULL
  COMMENT 'userNumbers'
);
CREATE UNIQUE INDEX message_no
  ON wb_push_msg (message_no);
CREATE INDEX message_no_2
  ON wb_push_msg (message_no);
CREATE INDEX phone_numbers
  ON wb_push_msg (user_numbers);
INSERT INTO wb_sequence VALUES ('seq_wb_push_msg', '1', '1');
SELECT nextval('seq_wb_push_msg');

#验证码表
DROP TABLE IF EXISTS `wb_sms_code`;
CREATE TABLE wb_sms_code
(
  id           BIGINT(15) PRIMARY KEY              NOT NULL
  COMMENT 'id' AUTO_INCREMENT,
  phone_number VARCHAR(50) DEFAULT ''              NOT NULL
  COMMENT 'phoneNumber',
  sms_code     VARCHAR(50) DEFAULT ''              NOT NULL
  COMMENT 'smsCode',
  sms_type     VARCHAR(50) DEFAULT ''              NOT NULL
  COMMENT 'smsType',
  available    TINYINT(3) DEFAULT '0'              NOT NULL
  COMMENT 'available',
  effect_time  DATETIME                            NOT NULL
  COMMENT 'effectTime',
  create_time  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  COMMENT 'createTime',
  update_time  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  COMMENT 'updateTime'
);
CREATE INDEX phone_number
  ON wb_sms_code (phone_number);

#安全控制
DROP TABLE IF EXISTS `wb_security_control`;
CREATE TABLE wb_security_control
(
  phone_number       VARCHAR(64) DEFAULT ''              NOT NULL
  COMMENT 'phoneNumber',
  control_type       VARCHAR(50) DEFAULT ''              NOT NULL
  COMMENT 'controlTypeEnum',
  mistake_times      INT(11) DEFAULT '-1'                NOT NULL
  COMMENT 'mistakeTimes',
  first_mistake_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  COMMENT 'firstMistakeTime',
  last_mistake_time  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  COMMENT 'lastMistakeTime',
  freezed            TINYINT(3) DEFAULT '0'              NOT NULL
  COMMENT 'freezed',
  id                 BIGINT(15) PRIMARY KEY              NOT NULL
  COMMENT 'id' AUTO_INCREMENT,
  create_time        TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  COMMENT 'createTime',
  update_time        TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  COMMENT 'updateTime'
);
CREATE UNIQUE INDEX last_mistake_time
  ON wb_security_control (last_mistake_time);
CREATE INDEX phone_number
  ON wb_security_control (phone_number);

#版本表
DROP TABLE IF EXISTS `wb_app_version`;
CREATE TABLE wb_app_version
(
  id           VARCHAR(50) PRIMARY KEY             NOT NULL,
  role_type    VARCHAR(20)                         NOT NULL,
  platform     VARCHAR(20)                         NOT NULL,
  version_code VARCHAR(20)                         NOT NULL,
  force_update SMALLINT(6) DEFAULT '0' NOT NULL,
  file_url     VARCHAR(512),
  description  VARCHAR(256)                        NOT NULL,
  operator     VARCHAR(50)                         NOT NULL,
  create_time  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  COMMENT 'createTime',
  update_time  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  COMMENT 'updateTime'
);
CREATE INDEX role_platform_version
  ON wb_app_version (role_type, platform, version_code);
INSERT INTO wb_sequence VALUES ('seq_wb_app_version', '1', '1');
SELECT nextval('seq_wb_app_version');

#api配置
DROP TABLE IF EXISTS `wb_api_config`;
CREATE TABLE wb_api_config
(
  id               BIGINT(15) PRIMARY KEY              NOT NULL
  COMMENT 'id' AUTO_INCREMENT,
  api_name         VARCHAR(50) DEFAULT ''              NOT NULL
  COMMENT 'apiName',
  api_host         VARCHAR(50) DEFAULT ''              NOT NULL
  COMMENT 'apiHost',
  api_uri          VARCHAR(100) DEFAULT ''             NOT NULL
  COMMENT 'apiUri',
  backend_class    VARCHAR(100) DEFAULT ''             NOT NULL
  COMMENT 'backendClass',
  backend_method   VARCHAR(256) DEFAULT ''             NOT NULL
  COMMENT 'backendMethod',
  need_session     TINYINT(3) DEFAULT '0'              NOT NULL
  COMMENT 'needSession',
  available        TINYINT(3) DEFAULT '0'              NOT NULL
  COMMENT 'available',
  param_valid_rule VARCHAR(256) DEFAULT ''             NOT NULL
  COMMENT 'paramValidRule',
  description      VARCHAR(50) DEFAULT ''              NOT NULL
  COMMENT 'description',
  operator         VARCHAR(50) DEFAULT ''              NOT NULL
  COMMENT 'operator',
  create_time      TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  COMMENT 'createTime',
  update_time      TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  COMMENT 'updateTime'
);
CREATE UNIQUE INDEX api_host_uri
  ON wb_api_config (api_host, api_uri);
CREATE INDEX api_uri
  ON wb_api_config (api_uri);

#密钥
DROP TABLE IF EXISTS `wb_user2private_key`;
CREATE TABLE wb_user2private_key
(
  user_number     VARCHAR(50)                         NOT NULL
  COMMENT 'userNumber',
  customer_number VARCHAR(50)                         NOT NULL
  COMMENT 'customerNumber',
  private_key     VARCHAR(3000)                       NOT NULL
  COMMENT 'privateKey',
  id              BIGINT(15) PRIMARY KEY              NOT NULL
  COMMENT 'id' AUTO_INCREMENT,
  create_time     TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  COMMENT 'createTime',
  update_time     TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  COMMENT 'updateTime'
);
CREATE INDEX create_time
  ON wb_user2private_key (create_time);
CREATE INDEX customer_number
  ON wb_user2private_key (customer_number);
CREATE INDEX update_time
  ON wb_user2private_key (update_time);
CREATE INDEX user_number
  ON wb_user2private_key (user_number);

#台牌
DROP TABLE IF EXISTS `wb_qr_code`;
CREATE TABLE wb_qr_code
(
  id              BIGINT(15) PRIMARY KEY              NOT NULL
  COMMENT 'id'                 AUTO_INCREMENT,
  qr_id           VARCHAR(50) DEFAULT ''              NOT NULL
  COMMENT 'qrId',
  qr_url          VARCHAR(128) DEFAULT ''             NOT NULL
  COMMENT 'qrUrl',
  customer_number VARCHAR(50)  DEFAULT ''
  COMMENT 'customerNumber',
  shop_number     VARCHAR(50)  DEFAULT ''
  COMMENT 'shopNumber',
  user_number     VARCHAR(50)  DEFAULT ''
  COMMENT 'userNumber',
  remark          VARCHAR(128) DEFAULT ''
  COMMENT 'remark',
  status          VARCHAR(32) DEFAULT ''              NOT NULL
  COMMENT 'status',
  relation_id     VARCHAR(50)  DEFAULT ''
  COMMENT 'relationId',
  create_time     TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  COMMENT 'createTime',
  update_time     TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  COMMENT 'updateTime'
);
CREATE INDEX create_time
  ON wb_qr_code (create_time);
CREATE INDEX customer_number
  ON wb_qr_code (customer_number);
CREATE INDEX qr_id
  ON wb_qr_code (qr_id);
CREATE INDEX relation_id
  ON wb_qr_code (relation_id);
CREATE INDEX shop_number
  ON wb_qr_code (shop_number);
CREATE INDEX update_time
  ON wb_qr_code (update_time);
CREATE INDEX user_number
  ON wb_qr_code (user_number);
INSERT INTO wb_sequence VALUES ('seq_wb_qrcode', '1', '1');