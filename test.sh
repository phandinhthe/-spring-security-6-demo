#!/bin/sh

# create token
token="$(curl --location 'http://localhost:8999/api/login' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username": "phandinhthe",
    "mailAddress": "phandintheh1991@gmail.com",
    "password": "123"
  }')"
#echo $token
#execute request
send="(curl --location 'http://localhost:8999/api/v1/user-management/users' --header 'Authorization: Bearer $token')"
eval $send
