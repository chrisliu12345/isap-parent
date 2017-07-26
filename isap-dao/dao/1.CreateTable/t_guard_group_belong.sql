-- t_guard_group_belong
DROP TABLE IF EXISTS t_guard_group_belong;
CREATE TABLE t_guard_group_belong
(
  id bigint NOT NULL AUTO_INCREMENT,
  group_id bigint NOT NULL COMMENT '小组 id',
  department_id char(36) NOT NULL COMMENT '社区 id',
  belong_desc varchar(255) default NULL COMMENT '描述',
  user_id bigint NOT NULL,
  add_time timestamp NOT NULL default CURRENT_TIMESTAMP,
  update_time timestamp NOT NULL default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT pk_t_guard_group_belong PRIMARY KEY (id),
  CONSTRAINT uk_t_guard_group_belong UNIQUE (group_id, department_id),
  CONSTRAINT fk_t_guard_group_belong_group_id FOREIGN KEY (group_id) 
       REFERENCES t_guard_group (id)
	   ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_t_guard_group_belong_department_id FOREIGN KEY (department_id) 
      REFERENCES t_department (id)
	  ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_t_guard_group_belong_user_id FOREIGN KEY (user_id)
	  REFERENCES t_user (id)
	  ON UPDATE CASCADE ON DELETE CASCADE
)COMMENT='保安小组社区绑定表';