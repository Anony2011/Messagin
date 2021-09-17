#!/bin/bash
export JASYPT_ENCRYPTOR_PASSWORD=$1
./mvnw package
docker build -t mssging-app/msg-spring-boot-docker .