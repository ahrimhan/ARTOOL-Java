#!/usr/bin/env python
import glob
import sys
import re
import os
import fnmatch
import pickle
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

if len(sys.argv) < 3 :
	print "invalud arguments"
	quit()

revStart = int(sys.argv[1])
revEnd = int(sys.argv[2])

changeList = dict()
currentDir = os.getcwd()
print currentDir

for r_int in range(revStart, revEnd + 1, 10) :
	rev = str(r_int)
	ast_file = currentDir + '/./ast_rev/ast_r'+rev+'.txt'
	changeList.clear()
	startTime = time()
	os.chdir('./rev/columba_r'+rev+'/')
	print ast_file + " is being calculated...",
	for root, dirnames, filenames in os.walk('.'):
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
	os.chdir('../../')
	print "done during " + str(elapsedTime)
