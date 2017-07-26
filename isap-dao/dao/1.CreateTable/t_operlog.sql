-- t_operlog 
DROP TABLE IF EXISTS t_operlog;
CREATE TABLE t_operlog
(
  id bigint NOT NULL AUTO_INCREMENT,
  oper_name varchar(128) NOT NULL COMMENT '操作员',
  oper_time datetime NOT NULL default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  ip_address varchar(64) NOT NULL COMMENT '登录IP地址',
  service_type tinyint(4) unsigned NOT NULL COMMENT '业务类型',
  oper_type tinyint(4) unsigned  NOT NULL COMMENT '操作类型',
  oper_result tinyint(1) unsigned NOT NULL COMMENT '操作结果',
  description varchar(1000) NOT NULL COMMENT '操作描述',
  failure_cause varchar(1000) NOT NULL default '' COMMENT '操作失败原因',
  add_time timestamp NOT NULL default CURRENT_TIMESTAMP,
  update_time timestamp NOT NULL default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT pk_t_operlog PRIMARY KEY(id)
)COMMENT= '操作日志表';