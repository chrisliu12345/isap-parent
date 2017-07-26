-- t_guard_group
DROP TABLE IF EXISTS t_guard_group;
CREATE TABLE t_guard_group
(
  id bigint NOT NULL AUTO_INCREMENT,
  name varchar(128) NOT NULL COMMENT '保安组名字',
  description varchar(255) default NULL COMMENT '保安组描述',
  user_id bigint NOT NULL,
  add_time timestamp NOT NULL default CURRENT_TIMESTAMP,
  update_time timestamp NOT NULL default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT pk_t_guard_group PRIMARY KEY (id),
  CONSTRAINT fk_t_guard_group_user_id FOREIGN KEY (user_id)
	  REFERENCES t_user (id)
	  ON UPDATE CASCADE ON DELETE CASCADE
)COMMENT= '保安组信息表';