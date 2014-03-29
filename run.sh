#!/bin/bash
mvn clean install && java -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=8000 -jar target/eneid-backend-1.0-SNAPSHOT.jar 
