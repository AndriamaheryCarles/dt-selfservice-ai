#!/bin/bash

echo "start the project with local env ..."
export LLM_ENV_FILE="llm/docker.local.env"

#echo "starting postgres db ..."
#docker-compose -f docker-compose.postgres.yml up -d

echo "starting ollama db ..."
docker-compose -f docker-compose.ollama.yml up -d

sleep 5

echo "starting dt-extraction-backend-info service ..."
docker-compose -f docker-compose.yml up --build -d

echo "viewing dt-extraction-backend-info container logs... press CTRL+c to exit"
docker-compose -f docker-compose.yml logs -f
    #-f docker-compose.postgres.yml logs -f