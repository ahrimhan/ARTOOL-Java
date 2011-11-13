#!/usr/bin/env python
import glob
import sys
import re
import os
import fnmatch
import pickle
from subprocess import Popen
from time import gmtime, strftime, time

def isOppositeChange(a, b) :
	for index in (0, 1, 2, 4):
		if a[index] != b[index] :
			return 0
	if a[3] == b[3] :
		return 0
	else:
		return 1

def addToChangeList(changeList, change)	:
	for prevChange in changeList :
		if isOppositeChange(prevChange, change) :
			changeList.remove(prevChange)
			newChange = ( change[0], change[1], change[2], '*', change[4] )
			changeList.append( newChange )
			return	
	changeList.append(change)

def addToChangeList(changeList, change)	:
	changeList.append(change)

if len(sys.argv) < 2 :
	print "invalud arguments"
	quit()

history_file_name = sys.argv[1]

changeList = dict()
currentDir = os.getcwd()
print currentDir


history_file = open(history_file_name)
isFirst = True;
r_int = 6981;

os.chdir('tomcat70')

for hashline in history_file :
	startTime = time()
	rev = str(r_int)
	print "git reset --hard " + hashline
	print os.popen("git reset --hard " + hashline).read()
	ast_file = currentDir + '/ast_rev/ast_r'+rev+'.txt'
	changeList.clear()
	print ast_file + " is being calculated...",
	os.chdir('./java')
	for root, dirnames, filenames in os.walk('org'):
		for filename in fnmatch.filter(filenames, '*.java'):
			path = os.path.join(root, filename)
			f = open(path)
			lineNumber = 0;
			className = None;
			prevMethod = None;
			prevLine = None;	
			c = None;
			m = None;
			for line in f:
				lineNumber = lineNumber + 1
				c = re.match(r"^\s*(public|private)?\s+class\s+([a-zA-Z_0-9]+)", line)
				if c :
					if className and prevMethod :
						if not path in changeList :
							changeList[path] = []	
						changeList[path].append((prevLine, lineNumber - 1, className, prevMethod))
					className = c.group(2)
				m = re.match(r"^\s*(public|private)?\s+[a-zA-Z_][a-zA-Z0-9_]* ([a-zA-Z_][a-zA-Z0-9_D]*)[(]([a-zA-Z0-9]*\s+[a-zA-Z_0-9]|[)])", line) 
				if m and className and m.group(2) != 'if' :
					if prevMethod and className :
						if not path in changeList :
							changeList[path] = []
						changeList[path].append((prevLine, lineNumber - 1, className, prevMethod))
					prevMethod = m.group(2)
					prevLine = lineNumber;

	outputFile = open(ast_file, 'wb')
	pickle.dump(changeList, outputFile)
	outputFile.close();
	elapsedTime = time() - startTime;
	print "done during " + str(elapsedTime)
	os.chdir('..')
	r_int = r_int - 1
	if r_int == 0 : 
		break

os.chdir('..');
