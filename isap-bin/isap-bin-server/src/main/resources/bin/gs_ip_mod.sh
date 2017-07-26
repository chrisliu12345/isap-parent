#!/bin/sh 
#gs_ip_mod.sh 191.191.16.59 191.191.16.254 

PROC=/bin/sed 
FILE_PATH=/etc/sysconfig/network-scripts/ifcfg-eth0 
IP_PRIMAL=`grep -i ipaddr $FILE_PATH|awk -F "=" '{printf $2 }'` 
MASK_PRIMAL=`grep -i netmask $FILE_PATH|awk -F "=" '{printf $2}'` 
GATEWAY=`grep -i gateway $FILE_PATH|awk -F "=" '{printf $2}'` 


#IP_MODI=192.168.0.160 
IP_MODI=$1
#MASK_MODI=255.255.255.0 
#MASK_MODI=$2

GATEWAY_MODI=$2

#-e "s/$MASK_PRIMAL/$MASK_MODI/g"
$PROC -e "s/$IP_PRIMAL/$IP_MODI/g"  -e "s/GATEWAY/$GATEWAY_MODI/g" $FILE_PATH > ifcfg-eth0.bak 
cp -pf ./ifcfg-eth0.bak $FILE_PATH 
cat $FILE_PATH 
#cat ifcfg-eth0.bak

service network  restart
#restart network command 
#/etc/init.d/network restart > /dev/null 2>&1
