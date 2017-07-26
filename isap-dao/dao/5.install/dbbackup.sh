#!/bin/sh

#数据库
MYSQL_USER=root
MYSQL_PASSWD=root
DATABASE=isap
MYSQL_DUMP=/opt/mysql/bin/mysqldump

#备份目录
BACKUP_DATE=`date +%Y%m%d`
BACKUP_DIR=/mnt/mysql/db_backup
BACKUP_FILE=$BACKUP_DIR/${BACKUP_DATE}_${DATABASE}bak.sql
BACKUP_LOG=$BACKUP_DIR/db_backup_log.log
BACKUP_FILE_NUM=30

database_backup()
{
	#删除当天已存在的备份文件
	if [ -f $BACKUP_FILE ]; then
		rm -f $BACKUP_FILE
	fi
	
	#备份
	echo "********** $BACKUP_DATE **********" >>$BACKUP_LOG
	echo "starting time: `date '+%Y-%m-%d %H:%M:%S'` " >>$BACKUP_LOG
	$MYSQL_DUMP -u$MYSQL_USER -p$MYSQL_PASSWD $DATABASE --ignore-table=${DATABASE}.t_operlog >>$BACKUP_FILE
	if [ $? -eq 0 ]; then
		echo "backup ok" >>$BACKUP_LOG
	else
		echo "backup err" >>$BACKUP_LOG
	fi
	
	#删除超过30天的备份文件
	dump_files=`find $BACKUP_DIR | grep "[0-9{8}]_${DATABASE}bak.sql" | sort | wc -l`
	if [ $dump_files -gt $BACKUP_FILE_NUM ]; then
		for file_delete in $BACKUP_DIR
		do
			delete_dump=`find $BACKUP_DIR -mtime $BACKUP_FILE_NUM | grep "[0-9{8}]_${DATABASE}bak.sql" | sort | head -n 1`
			rm -f $delete_dump
			echo "$delete_dump deleted" >>$BACKUP_LOG
		done
	fi
	echo "ending time: `date '+%Y-%m-%d %H:%M:%S'` " >>$BACKUP_LOG
}

backup()
{
	if [ ! -d $BACKUP_DIR ]; then
		mkdir -p $BACKUP_DIR
	fi
	
	database_backup
}

backup

