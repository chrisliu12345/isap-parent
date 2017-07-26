-- t_version_history
DROP TABLE IF EXISTS t_version_history;
CREATE TABLE t_version_history
(
  id bigint NOT NULL AUTO_INCREMENT,
  install_flag tinyint(4) unsigned NOT NULL default 0 COMMENT '安装标志位,默认升级',
  from_inner_version varchar(32) NOT NULL COMMENT '起始内部版本号',
  to_inner_version varchar(32) NOT NULL COMMENT '升级内部版本号',
  add_time timestamp NOT NULL default CURRENT_TIMESTAMP,
  update_time timestamp NOT NULL default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT pk_t_version_history PRIMARY KEY(id)
)COMMENT= '版本升级历史表';