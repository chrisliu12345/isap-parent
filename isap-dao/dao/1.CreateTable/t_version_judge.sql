-- t_version_judge
DROP TABLE IF EXISTS t_version_judge;
CREATE TABLE t_version_judge
(
  id bigint NOT NULL AUTO_INCREMENT,
  src_version varchar(32) NOT NULL COMMENT '起始版本号',
  curr_version varchar(32) NOT NULL COMMENT '当前版本号',
  target_version varchar(32) NOT NULL COMMENT '目标版本号',
  add_time timestamp NOT NULL default CURRENT_TIMESTAMP,
  update_time timestamp NOT NULL default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT pk_t_version_judge PRIMARY KEY(id)
)COMMENT= '版本裁决表';