-- t_alert_log
DROP TABLE IF EXISTS t_alert_log;
CREATE TABLE t_alert_log
(
  id bigint NOT NULL AUTO_INCREMENT,
  alert_id bigint NOT NULL COMMENT '警情 id',
  user_id bigint NOT NULL COMMENT '用户 id',
  reference_id bigint default NULL COMMENT '根据 log_type 不同，object id 会有不同的含义。',
  log_type tinyint(4) unsigned NOT NULL COMMENT '日志类型\n0:XXX 将警情标记为 可疑警情\n1:XXX 联系保安 XXX-17012345678 出警\n2:XXX 联系保安 XXX-17012345678 失败，原因: XXX \n3:XXX 确认保安 XXX-17012345678 到达现场\n4:XXX 确认保安 XXX-17012345678 未到达现场，原因: XXX\n5:XXX 将警情标记为已盘问可疑人员\n6:XXX 将警情标记为未盘问可疑人员\n7:XXX 收到保安二次回电，内容: XXX\n8:XXX 将警情标记为处理失败，原因: XXX\n9:XXX 30 分钟内未确认保安是否到达现场，系统将警情标记为处理失败\n10:XXX 30 分钟内未确认收到保安二次回电，系统将警情标记为处理完成',
  content varchar(255) default NULL,
  reason varchar(255) default NULL,
  add_time timestamp NOT NULL default CURRENT_TIMESTAMP,
  update_time timestamp NOT NULL default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT pk_t_alert_log PRIMARY KEY (id),
  CONSTRAINT fk_t_alert_log_alert_id FOREIGN KEY (alert_id) 
      REFERENCES t_alert (id)
	  ON UPDATE CASCADE ON DELETE CASCADE
);
