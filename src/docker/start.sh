#!/usr/bin/env bash
wait-for-port.sh $DB_HOST:$DB_PORT -t 15
java -Djava.security.egd=file:/dev/./urandom -jar /picty/api.picty.io.jar --spring.config.location=/picty/application.properties