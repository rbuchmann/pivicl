#!/bin/env python

import argparse
import sys
import os

parser = argparse.ArgumentParser(description="Write multiple svgs from stdin to files")
parser.add_argument('-o', '--outfile', metavar='OUTFILE', default='output.svg')

args = parser.parse_args()

base, extension = os.path.splitext(args.outfile)


def write_files(collection):
    for i,s in enumerate(collection):
        f = open(base + "%06d" % i + extension, 'w')
        f.write(s)
        f.close()

write_files(sys.stdin.readlines())
