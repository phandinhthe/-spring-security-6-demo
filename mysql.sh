#!/bin/bash

run="$(docker run -d \
	--name mysql-local-server \
	-e MYSQL_ROOT_PASSWORD=my_password \
  -e MYSQL_USER=admin \
  -e MYSQL_PASSWORD=password \
  -e MYSQL_DATABASE=spring_security \
  -v ./mysql-data:/docker-entrypoint-initdb.d \
  -p 13306:3306 \
	mysql:8.4.0)"

echo $run