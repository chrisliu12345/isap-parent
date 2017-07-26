-- t_user_belong
DROP TABLE IF EXISTS t_user_belong;
CREATE TABLE t_user_belong
(
  id bigint NOT NULL AUTO_INCREMENT,
  user_id bigint NOT NULL COMMENT '用户id',
  role_id bigint NOT NULL COMMENT '角色id',
  add_time timestamp NOT NULL default CURRENT_TIMESTAMP,
  update_time timestamp NOT NULL default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT pk_t_user_belong PRIMARY KEY (id),
  CONSTRAINT fk_t_user_belong_user_id FOREIGN KEY (user_id) 
      REFERENCES t_user (id)
	  ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_t_user_belong_role_id FOREIGN KEY (role_id) 
      REFERENCES t_role (id)
	  ON UPDATE CASCADE ON DELETE CASCADE
)COMMENT= '用户所属信息表';