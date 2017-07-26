-- t_role_dep
DROP TABLE IF EXISTS t_role_dep;
CREATE TABLE t_role_dep
(
  id bigint NOT NULL AUTO_INCREMENT,
  role_id bigint NOT NULL COMMENT '角色id',
  department_id char(36) NOT NULL COMMENT '部门id',
  add_time timestamp NOT NULL default CURRENT_TIMESTAMP,
  update_time timestamp NOT NULL default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT pk_t_role_dep PRIMARY KEY(id),
  CONSTRAINT fk_t_role_dep_role_id FOREIGN KEY(role_id) 
      REFERENCES t_role (id)
	  ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_t_role_dep_department_id FOREIGN KEY (department_id) 
      REFERENCES t_department (id)
	  ON UPDATE CASCADE ON DELETE CASCADE
)COMMENT= '角色部门表';