#!/usr/bin/env python
import glob
import sys
import re
import os
import fnmatch
import pickle
from subprocess import Popen
from time import gmtime, strftime, time


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

os.chdir('./tomcat70')
prev_hashline = None

for hashline in history_file :
	rev = str(r_int)
	prev = str(r_int + 1)
	hashline = hashline.strip()

	if prev_hashline :
		startTime = time()
		git_command = "git diff " + prev_hashline + " " + hashline + " ./java"
		print git_command

		diff_str = os.popen(git_command).read()
		diff_file = currentDir + '/diff/' + rev + "_" + prev+'.diff'
		outputFile = open(diff_file, 'wb')
		
		print >>outputFile, diff_str
		
		outputFile.close();
		elapsedTime = time() - startTime;
		print "done during " + str(elapsedTime)
		r_int = r_int - 1
	
	prev_hashline = hashline
	if r_int == 0 : 
		break

os.chdir('..');
