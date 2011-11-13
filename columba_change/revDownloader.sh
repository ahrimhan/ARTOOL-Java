#!/bin/sh

URL=https://columba.svn.sourceforge.net/svnroot/columba/columba/trunk
 
STEP=10
STARTREV=$1
if [ $# -ne 3 ]
then
	echo "Usage: `basename $0` <STARTREV> <ENDREV> <DOWNLOAD_DIR>"
	exit 65
fi

mkdir -p $3
cd $3
for i in `seq $1 $STEP $2`; do
	STARTREV=$i
	echo "Checkout  ${STARTREV}"
	svn checkout ${URL}@${STARTREV} columba_r${STARTREV}
done
