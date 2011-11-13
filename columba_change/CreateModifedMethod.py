#!/usr/bin/env python
import glob
import sys
import re
import os
import fnmatch
import pickle

if len(sys.argv) < 4 :
	print sys.argv[0] + " <revStart> <revEnd> <change_set_dir>"
	quit()

revStart = int(sys.argv[1])
revEnd = int(sys.argv[2])
change_set_dir = sys.argv[3]

outputFile = open("resultARTool/modifiedMethod.csv", "wb")

revS = str(revStart);
revE = str(revEnd);
change_set_file_path = change_set_dir + '/' + revS + '_' + revE + '.txt'
print "Analyzing " + change_set_file_path
change_set_file = open(change_set_file_path)
changeSetList = pickle.load(change_set_file)
change_set_file.close()
for changeSet in changeSetList :
	(pathName, classShortName, methodName, lineNumber, sRev, eRev) = changeSet
	_tempClassName = re.sub(r'^\.\.[a-z]+\.src\.main\.java\.', r'',  re.sub(r'/', r'.', re.sub(r'\.java', r'', pathName)));
	m = re.match(r'^\.\.[a-z]+\.src\.test\.java\.', _tempClassName)
	if m :
		continue
	m = re.match(r'^\.\.plugins', _tempClassName)
	if m :
		continue

	classLongName = re.sub(r'^(.*\.)[^.]*$', r'\1' + classShortName, _tempClassName);
	if _tempClassName != classLongName :
		classLongName = _tempClassName + "$" + classShortName
	outputFile.write(classLongName + "," + methodName + "," + str(lineNumber) + "\n")

outputFile.close()

