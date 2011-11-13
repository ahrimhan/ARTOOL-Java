#!/bin/sh

URL=https://columba.svn.sourceforge.net/svnroot/columba/columba/trunk
 
STEP=10
STARTREV=$1
INITREV=`expr $1 + $STEP`
if [ $# -ne 3 ]
then
	echo "Usage: `basename $0` <STARTREV> <ENDREV> <DOWNLOAD_DIR>"
	exit 65
fi

mkdir -p $3

cd rev/

for i in `seq $INITREV $STEP $2`; do
	ENDREV=$i
	echo "Diff from ${STARTREV} to ${ENDREV}"
	diff -u -x .svn* -r columba_r${STARTREV} columba_r${ENDREV} > ../$3/${STARTREV}_${ENDREV}.diff
	STARTREV=$i
done

cd ..
