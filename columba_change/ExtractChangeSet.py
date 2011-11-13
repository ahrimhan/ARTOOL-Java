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


for r_int in range(revStart, revEnd, 10) :
	rev = str(r_int);
	ast_file_path = astDirectory + '/ast_r' + rev + '.txt'
	ast_file = open(ast_file_path)
	changeList = pickle.load(ast_file)
	ast_file.close()
	diff_file_path = diffDirectory + '/' + rev + '_' + str(r_int + 10) + '.diff'
	diff_file = open(diff_file_path, 'r')
	diff_block_is_started = int(0)
	curChangeSet = []
	index_detected = 0
	for line in diff_file :
		m = re.match(r"Index: (.*.java)$", line) 
		if m :
			curFile =  './'+ m.group(1)
			print curFile
			if curFile in changeList :
				index_detected = 1
				curChangeSet = changeList[curFile]
			else :
				index_detected = 0
			diff_block_is_started = int(0)
			continue
		m = re.match(r"@@ \-([0-9]+),[0-9]+\s+\+([0-9]+),[0-9]+ @@", line)
		if m and index_detected:
			print m.group(1)
			diff_block_is_started = int(m.group(1))
			continue
		if diff_block_is_started > 0 :
			m = re.match(r"^[+-]", line)
			if m :
				changeItem = getChangeItem(curFile, curChangeSet, diff_block_is_started, rev, str(r_int + 10))
				if changeItem :
					changeItemList.append(changeItem)
				diff_block_is_started = 0	
			else :
				diff_block_is_started = diff_block_is_started + 1


cs_file_name = "./changeset/" + str(revStart) + "_" + str(revEnd) + ".txt"
print cs_file_name + " is dumped...",
outputFile = open(cs_file_name, "wb")
pickle.dump(changeItemList, outputFile)
changeItemList = []
outputFile.close()
print "Done."
