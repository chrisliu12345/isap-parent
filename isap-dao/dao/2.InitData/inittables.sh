#!/bin/sh

init_isap_tables()
{
	mysql -uroot -p$PASSWORD -D$ISAPDATABASE<$SOFTSRCDIR/init_tables/t_alarm_type.sql >>/dev/null 2>&1
	if [ $? -ne 0 ];then
		echo "init t_alarm_type table failed!"
		return 1
	fi
    
	mysql -uroot -p$PASSWORD -D$ISAPDATABASE<$SOFTSRCDIR/init_tables/t_alert_log_template.sql >>/dev/null 2>&1
	if [ $? -ne 0 ];then
		echo "init t_alert_log_template table failed!"
		return 1
	fi
	
	mysql -uroot -p$PASSWORD -D$ISAPDATABASE<$SOFTSRCDIR/init_tables/t_dev_authdef.sql >>/dev/null 2>&1
	if [ $? -ne 0 ];then
		echo "init t_dev_authdef table failed!"
		return 1
	fi
	
	mysql -uroot -p$PASSWORD -D$ISAPDATABASE<$SOFTSRCDIR/init_tables/t_enum.sql >>/dev/null 2>&1
	if [ $? -ne 0 ];then
		echo "init t_enum table failed!"
		return 1
	fi
	
	mysql -uroot -p$PASSWORD -D$ISAPDATABASE<$SOFTSRCDIR/init_tables/t_menu_authdef.sql >>/dev/null 2>&1
	if [ $? -ne 0 ];then
		echo "init t_menu_authdef table failed!"
		return 1
	fi
	
	mysql -uroot -p$PASSWORD -D$ISAPDATABASE<$SOFTSRCDIR/init_tables/t_user.sql >>/dev/null 2>&1
	if [ $? -ne 0 ];then
		echo "init t_user table failed!"
		return 1
	fi
	
	mysql -uroot -p$PASSWORD -D$ISAPDATABASE<$SOFTSRCDIR/init_tables/t_department.sql >>/dev/null 2>&1
	if [ $? -ne 0 ];then
		echo "init t_department table failed!"
		return 1
	fi
}

SOFTSRCDIR=$(cd `dirname $0`; pwd)
PASSWORD=$1
ISAPDATABASE=$2

init_isap_tables
