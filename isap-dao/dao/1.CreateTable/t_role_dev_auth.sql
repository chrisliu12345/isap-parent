-- t_role_dev_auth
DROP TABLE IF EXISTS t_role_dev_auth;
CREATE TABLE t_role_dev_auth
(
  id bigint NOT NULL AUTO_INCREMENT,
  role_id bigint NOT NULL COMMENT '角色id',
  auth_id bigint NOT NULL COMMENT '设备权限id',
  dev_id char(36) NOT NULL COMMENT '设备id',
  add_time timestamp NOT NULL default CURRENT_TIMESTAMP,
  update_time timestamp NOT NULL default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT pk_t_role_dev_auth PRIMARY KEY (id),
  CONSTRAINT uk_t_role_dev_auth UNIQUE (role_id, dev_id, auth_id),
  CONSTRAINT fk_t_role_dev_auth_role_id FOREIGN KEY (role_id) 
      REFERENCES t_role (id)
	  ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_t_role_dev_auth_auth_id FOREIGN KEY (auth_id) 
      REFERENCES t_dev_authdef (id)
	  ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_t_role_dev_auth_dev_id FOREIGN KEY (dev_id) 
      REFERENCES t_device (id)
	  ON UPDATE CASCADE ON DELETE CASCADE
)COMMENT '角色设备权限表';