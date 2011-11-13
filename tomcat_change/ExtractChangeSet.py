#!/usr/bin/env python
import glob
import sys
import re
import os
import fnmatch
import pickle
from time import gmtime, strftime, time

def getChangeItem(pathName, changeList, line, sRev, eRev):
	for change in changeList :
		if change[0] <= line and line <= change[1] :
#			print pathName + '(' + change[2] + ',' + change[3] + ')'
			return (pathName, change[2], change[3], change[0], sRev, eRev)
		


if len(sys.argv) < 5 :
	print sys.argv[0] + " <revStart> <revEnd> <ast_dir> <diff_dir>"
	quit()

revStart = int(sys.argv[1])
revEnd = int(sys.argv[2])
astDirectory = sys.argv[3]
diffDirectory = sys.argv[4]
changeItemList = []


csIdx = 0;
for r_int in range(revStart, revEnd + 1, 1) :
	rev = str(r_int);
	ast_file_path = astDirectory + '/ast_r' + rev + '.txt'
	ast_file = open(ast_file_path)
	changeList = pickle.load(ast_file)
	ast_file.close()
	diff_file_path = diffDirectory + '/' + rev + '_' + str(r_int + 1) + '.diff'
	diff_file = open(diff_file_path, 'r')
	diff_block_is_started = int(0)
	curChangeSet = []
	index_detected = 0
	for line in diff_file :
		m = re.match(r"--- a/java/(.*.java)$", line) 
		if m :
			curFile = m.group(1)
			print curFile
			if curFile in changeList :
				index_detected = 1
				curChangeSet = changeList[curFile]
			else :
				index_detected = 0
			diff_block_is_started = int(0)
			continue
		m = re.match(r"@@ \-([0-9]+),\s*[0-9]+ \+([0-9]+),\s*[0-9]+ @@", line)
		if m and index_detected:
			diff_block_is_started = int(m.group(1))
			continue
		if diff_block_is_started > 0 :
			m = re.match(r"^[+-]", line)
			if m :
				changeItem = getChangeItem(curFile, curChangeSet, diff_block_is_started, rev, str(r_int + 1))
				if changeItem :
					changeItemList.append(changeItem)
				diff_block_is_started = 0	
			else :
				diff_block_is_started = diff_block_is_started + 1

	csIdx = csIdx + 1
	if csIdx == 10 :
		cs_file_name = "./changeset/" + str(r_int - 9) + "_" + str(r_int) + ".txt"
		print cs_file_name + " is dumped...",
		outputFile = open(cs_file_name, "wb")
		pickle.dump(changeItemList, outputFile)
		changeItemList = []
		outputFile.close()
		print "Done."
		csIdx = 0

cs_file_name = "./changeset/" + str(int(r_int / 10)*10) + "_" + str(r_int) + ".txt"
print cs_file_name + " is dumped...",
outputFile = open(cs_file_name, "wb")
pickle.dump(changeItemList, outputFile)
changeItemList = []
outputFile.close()
print "Done."
csIdx = 0
