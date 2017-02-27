#!/bin/sh
ant -f build-dist.xml clean
ant -f build-dist.xml gendocs
ant -f build-dist.xml create-zip
ant -f build-dist.xml create-src-zip
echo "creating javadocs tar file"
tar cf javadocs.tar doc
