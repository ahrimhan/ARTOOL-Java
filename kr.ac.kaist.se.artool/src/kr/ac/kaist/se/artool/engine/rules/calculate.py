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

changedFile = open(sys.argv[1])
testFile = open(sys.argv[2])
changeList = dict()

for line in changedFile :
	c = re.match(r"([^,]+),([0-9]+)", line)
	if c :
		className = c.group(1)
		count = int(c.group(2))
		changeList[className] = count

score = 0;
for line in testFile :
	c = re.match(r"([^,]+),([0-9]+)", line)
	if c :
		if c.group(1) in changeList :
			print c.group(1)
			score = score + changeList[c.group(1)] 

print score
testFile.close()
changedFile.close()
