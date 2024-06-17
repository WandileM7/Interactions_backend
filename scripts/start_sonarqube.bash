#!/bin/bash
docker volume create --name sonarqube_data
docker volume create --name sonarqube_logs
docker volume create --name sonarqube_extensions
docker run --rm -p 9000:9000 -v sonarqube_extensions:/opt/sonarqube/extensions sonarqube:9.9.5-community