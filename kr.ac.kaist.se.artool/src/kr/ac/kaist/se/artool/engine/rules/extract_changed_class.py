#!/usr/bin/env python
import glob
import sys
import re
import os
import fnmatch
import pickle
from time import gmtime, strftime, time

if len(sys.argv) < 3 :
	print "invalud arguments"
	quit()

inputFile = open(sys.argv[1])
outputFile = open(sys.argv[2], 'wb')
changeList = dict()

for line in inputFile :
	c = re.match(r"([^,]+),[^,]+,([0-9]+)", line)
	if c :
		className = c.group(1)
		count = int(c.group(2))
		if not className in changeList :
			changeList[className] = 1
		else :
			changeList[className] = changeList[className] + 1

for key, value in changeList.items() :
	print >>outputFile, key+","+str(value)

inputFile.close()
outputFile.close()
