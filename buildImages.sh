#!/bin/bash

cd microservices
docker build -f Dockerfile-krakend -t krakend-image .

cd backend-nuxt-krakenD
docker build -f Dockerfile-resource -t backend-nuxt-krakend-img .

cd ../generatejson
docker build -f Dockerfile-generatejson -t generator-json-img .

cd ../login
docker build -f Dockerfile-login -t login-img .

