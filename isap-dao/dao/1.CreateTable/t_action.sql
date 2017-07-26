-- t_action
DROP TABLE IF EXISTS t_action;
CREATE TABLE t_action
(
  id bigint NOT NULL AUTO_INCREMENT,
  name varchar(128) NOT NULL COMMENT '动作名称',
  param_num tinyint(4) NOT NULL COMMENT '动作参数数目',
  linkage_id bigint NOT NULL COMMENT '联动业务ID',
  add_time timestamp NOT NULL default CURRENT_TIMESTAMP,
  update_time timestamp NOT NULL default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT pk_t_action PRIMARY KEY (id),
  CONSTRAINT fk_t_action_linkage_id FOREIGN KEY (linkage_id) 
      REFERENCES t_action_linkage (id)
	  ON UPDATE CASCADE ON DELETE CASCADE
)COMMENT='联动动作表';