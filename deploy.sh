#!/usr/bin/env bash

/usr/local/apache-tomcat-8.0.33/bin/shutdown.sh
rm /usr/local/apache-tomcat-8.0.33/webapps/JerseyTemplate*
cp build/libs/xkcddata.war /usr/local/apache-tomcat-8.0.33/webapps/
/usr/local/apache-tomcat-8.0.33/bin/startup.sh # sometimes requires sudo