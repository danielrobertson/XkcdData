#!/usr/bin/env bash

/usr/local/apache-tomcat-8.0.33/bin/shutdown.sh
rm -rf /usr/local/apache-tomcat-8.0.33/webapps/xkcddata*
cp build/libs/xkcddata.war /usr/local/apache-tomcat-8.0.33/webapps/
/usr/local/apache-tomcat-8.0.33/bin/startup.sh # sometimes requires sudo
