#!/bin/bash

echo "stopping the project with local docker env"
docker-compose -f docker-compose.yml -f docker-compose.ollama.yml down
#-f docker-compose.postgres.yml
