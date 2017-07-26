-- t_action_param
DROP TABLE IF EXISTS t_action_param;
CREATE TABLE t_action_param
(
  id bigint NOT NULL AUTO_INCREMENT,
  name varchar(128) NOT NULL COMMENT '参数名称',
  param_value varchar(128) NOT NULL COMMENT '参数值',
  param_sequence tinyint(4) unsigned NOT NULL COMMENT '参数序号',
  action_id bigint NOT NULL COMMENT '动作ID',
  add_time timestamp NOT NULL default CURRENT_TIMESTAMP,
  update_time timestamp NOT NULL default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT pk_t_action_param PRIMARY KEY (id),
  CONSTRAINT fk_t_action_param FOREIGN KEY (action_id) 
      REFERENCES t_action (id)
	  ON UPDATE CASCADE ON DELETE CASCADE
)COMMENT= '联动动作参数表';