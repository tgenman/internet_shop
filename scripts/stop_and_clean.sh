#!/bin/bash
cd ..
docker-compose rm -v
docker stop $(docker ps -a -q)
docker rmi -f $(docker images -q)
docker system prune


## Delete all stopped containers
#docker rm $( docker ps -q -f status=exited)
## Delete all dangling (unused) images
#docker rmi $( docker images -q -f dangling=true)