-- t_alarm_type
DROP TABLE IF EXISTS t_alarm_type;
CREATE TABLE t_alarm_type
(
  alarm_type bigint NOT NULL COMMENT '告警类型',
  alarm_name varchar(128) NOT NULL COMMENT '告警类型名称',
  alarm_level tinyint(4) unsigned NOT NULL COMMENT '告警等级',
  CONSTRAINT pk_t_alarm_type PRIMARY KEY(alarm_type)
)COMMENT '告警类型表';