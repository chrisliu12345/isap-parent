-- t_alert_log_template
DROP TABLE IF EXISTS t_alert_log_template;
CREATE TABLE t_alert_log_template
(
  id int NOT NULL AUTO_INCREMENT,
  log_type tinyint(4) unsigned NOT NULL COMMENT '日志类型',
  content varchar(128) NOT NULL COMMENT '内容',
  params_count tinyint(4) unsigned NOT NULL COMMENT '参数个数',
  update_time datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT pk_t_alert_log_template PRIMARY KEY (id),
  CONSTRAINT uk_t_alert_log_template UNIQUE (log_type)
)COMMENT= '警情处理日志模版表';