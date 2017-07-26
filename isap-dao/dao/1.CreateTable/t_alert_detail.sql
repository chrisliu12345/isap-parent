-- t_alert_detail
DROP TABLE IF EXISTS t_alert_detail;
CREATE TABLE t_alert_detail
(
  id bigint NOT NULL AUTO_INCREMENT,
  alert_id bigint NOT NULL COMMENT '警情 id',
  user_response_time int NOT NULL COMMENT '用户反应用时',
  user_process_time int NOT NULL COMMENT '用户处理用时',
  guard_response_time int NOT NULL COMMENT '保安出警用时',
  guard_process_time int NOT NULL COMMENT '保安处理用时',
  total_time int NOT NULL COMMENT '警情处理用时',
  false_alerts int NOT NULL default 0 COMMENT '警情发生到开始处理,期间误报警情数',
  confirm_alerts int NOT NULL default 0 COMMENT '警情发生到开始处理,期间确认过的警情数',
  unprocessed_alerts int NOT NULL default 0 COMMENT '警情发生前未处理的警情数',
  is_call_back tinyint(1) unsigned NOT NULL default 1 COMMENT '是否有回电',
  is_second_call_back tinyint(1) unsigned NOT NULL default 0 COMMENT '是否有二次回电',
  is_arrived tinyint(1) unsigned NOT NULL default 0,
  is_question_suspect tinyint(1) unsigned NOT NULL default 0 COMMENT '是否询问可疑人员',
  is_question_suspect_confirmed tinyint(1) unsigned NOT NULL default 0 COMMENT '保安询问可疑人员是否被确认',
  is_user_over_time tinyint(1) unsigned NOT NULL default 0 COMMENT '指挥中心人员处理是否超时',
  is_guard_over_time tinyint(1) unsigned NOT NULL default 0 COMMENT '保安出警是否超时',
  failed_reason_type tinyint(4) unsigned NOT NULL default 0 COMMENT '警情处理失败类型\n0:处理成功\n1:未联系上保安\n2:保安出警超时',
  failed_reason varchar(255) default NULL COMMENT '警情处理失败原因，详细描述',
  add_time timestamp NOT NULL default CURRENT_TIMESTAMP,
  update_time timestamp NOT NULL default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,   
  CONSTRAINT pk_t_alert_detail PRIMARY KEY (id),
  CONSTRAINT fk_t_alert_detail_alert_id FOREIGN KEY (alert_id) 
      REFERENCES t_alert (id)
	  ON UPDATE CASCADE ON DELETE CASCADE
)COMMENT='统计已经处理完成的警情信息';