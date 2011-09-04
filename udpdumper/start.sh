#/bin/sh

TIME=`date "+%Y%m%d-%H%M%S"`

MCPORT=18001
FAPORT=18002
MCFILE=./log/methodcall-${TIME}.log
FAFILE=./log/fieldaccess-${TIME}.log


./udpdumper.py 0.0.0.0 $MCPORT $MCFILE > /dev/null &
MCPID=$!
echo $MCPID > /tmp/mc.pid
./udpdumper.py 0.0.0.0 $FAPORT $FAFILE > /dev/null &
FAPID=$!
echo $FAPID > /tmp/fa.pid

