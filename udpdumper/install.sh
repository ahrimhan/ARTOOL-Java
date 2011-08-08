#!/bin/sh

DYNAMIC_PROFILE_DIR=/Users/ah-rimhan/Work/runtime-EclipseApplication/jedit/dynamic_profile
FA_PREFIX=fieldaccess
MC_PREFIX=methodcall

for i in `find ./log -name "${MC_PREFIX}*.log"` ; do
	echo $i
done
echo "Selected:"$i
cp $i ${DYNAMIC_PROFILE_DIR}/${MC_PREFIX}.log

for i in `find ./log -name "${FA_PREFIX}*.log"` ; do
	echo $i
done
echo "Selected:"$i
cp $i ${DYNAMIC_PROFILE_DIR}/${FA_PREFIX}.log
