#!/usr/bin/env bash

CATALINA_HOME="/usr/local/apache-tomcat-8.0.33"

$CATALINA_HOME/bin/shutdown.sh
rm -rf $CATALINA_HOME/webapps/xkcddata*
cp build/libs/xkcddata.war $CATALINA_HOME/webapps/
$CATALINA_HOME/bin/startup.sh # sometimes requires sudo
