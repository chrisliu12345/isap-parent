-- t_enum
DROP TABLE IF EXISTS t_enum;
CREATE TABLE t_enum
(
  enum_key varchar(36) NOT NULL COMMENT '键',
  enum_value int unsigned NOT NULL COMMENT '值',
  label varchar(128) NOT NULL COMMENT '名称',
  description varchar(255) NOT NULL COMMENT '描述'
)COMMENT= '枚举表';
CREATE INDEX idx_t_enum_enum_key ON t_enum (enum_key(10));