#!/usr/bin/env python
import pprint
import sys
import pickle


f = open(sys.argv[1])
c = pickle.load(f)

pprint.pprint(c)
