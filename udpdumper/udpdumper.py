#!/usr/bin/env python
import os
import sys
import socket

sock = None
fp = None
CURDIR = os.path.abspath(os.path.dirname(__file__))

def main():
    if len(sys.argv) != 4:
        print "Usage: %s [host] [port] [out_file]" % sys.argv[0]
        sys.exit(1)

    try:
        host = sys.argv[1]
        port = int(sys.argv[2])
        out_filename = sys.argv[3]
    except Exception:
        print "Wrong argument."
        print "Usage: %s [host] [port] [out_file]" % sys.argv[0]
        sys.exit(1)

    out_filename = os.path.join(CURDIR, out_filename)

    global sock
    sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    sock.bind((host, port))

    print "Opening UDP socket on %s:%s" % (host, port)
    print "Output file is %s" % out_filename

    global fp
    fp = open(out_filename, "wb")

    while True:
        data = sock.recv(1024)
        print "Received data. (Length: %s)" % len(data)
        fp.write(data)
        fp.flush()


if __name__ == "__main__":
    try:
        main()
    except KeyboardInterrupt:
        if sock:
            print "Closing socket..."
            sock.close()
        if fp:
            print "Closing file..."
            fp.flush()
            fp.close()
        print "Terminating program..."
