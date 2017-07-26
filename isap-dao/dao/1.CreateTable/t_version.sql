-- t_version
DROP TABLE IF EXISTS t_version;
CREATE TABLE t_version
(
  id bigint NOT NULL AUTO_INCREMENT,
  version_type varchar(16) NOT NULL COMMENT '版本类型',
  inner_version varchar(32) NOT NULL COMMENT '内部版本号',
  outer_version varchar(64) NOT NULL COMMENT '外部版本号',
  add_time timestamp NOT NULL default CURRENT_TIMESTAMP,
  update_time timestamp NOT NULL default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT pk_t_version PRIMARY KEY(id)
)COMMENT= '版本表';