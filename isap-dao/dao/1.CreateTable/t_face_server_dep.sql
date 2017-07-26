-- t_face_server_dep
DROP TABLE IF EXISTS t_face_server_dep;
CREATE TABLE t_face_server_dep
(
  id bigint NOT NULL AUTO_INCREMENT,
  server_id bigint NOT NULL COMMENT '服务器id',
  department_id char(36) NOT NULL COMMENT '部门id',
  add_time timestamp NOT NULL default CURRENT_TIMESTAMP,
  update_time timestamp NOT NULL default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT pk_t_face_server_dep PRIMARY KEY (id),
  CONSTRAINT uk_t_face_server_dep UNIQUE (server_id, department_id),
  CONSTRAINT fk_t_face_server_dep_server_id FOREIGN KEY (server_id)
	  REFERENCES t_face_server (id)
	  ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_t_face_server_dep_department_id FOREIGN KEY (department_id) 
      REFERENCES t_department (id)
	  ON UPDATE CASCADE ON DELETE CASCADE
)COMMENT= '人脸服务器小区表';