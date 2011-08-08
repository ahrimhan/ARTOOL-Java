#!/bin/sh

kill -9 `cat /tmp/mc.pid`
kill -9 `cat /tmp/fa.pid`
rm /tmp/mc.pid
rm /tmp/fa.pid
