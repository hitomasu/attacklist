#!/bin/bash

cd `dirname $0`
. _script-env.sh

echo ""
echo "==============================================================================="
echo "                                                         Install 'Common' project"
echo "                                                         ======================"
JAVA_HOME=`/usr/libexec/java_home -v 1.7` mvn -e install
