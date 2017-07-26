-- t_guard
DROP TABLE IF EXISTS t_guard;
CREATE TABLE t_guard
(
  id bigint NOT NULL AUTO_INCREMENT,
  name varchar(16) NOT NULL,
  phone char(11) NOT NULL,
  guard_desc varchar(255) default NULL COMMENT '保安描述',
  group_id bigint NOT NULL default 0,
  cid char(18) default NULL COMMENT '身份证',
  home_address varchar(128) default NULL COMMENT '家庭住址',
  birthday date default NULL COMMENT '生日',
  user_id bigint NOT NULL,
  add_time timestamp NOT NULL default CURRENT_TIMESTAMP,
  update_time timestamp NOT NULL default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT pk_t_guard PRIMARY KEY (id),
  CONSTRAINT fk_t_guard_group_id FOREIGN KEY (group_id) 
      REFERENCES t_guard_group (id)
	  ON UPDATE CASCADE ON DELETE NO ACTION,
  CONSTRAINT fk_t_guard_user_id FOREIGN KEY (user_id)
	  REFERENCES t_user (id)
	  ON UPDATE CASCADE ON DELETE CASCADE
)COMMENT='保安信息';