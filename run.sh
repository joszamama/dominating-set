#!/bin/bash

# Build the Docker image
docker build -t mdsp .

# Run the container and execute the tests
docker run --name mdsp -it mdsp mvn test

# Stop the container
docker stop mdsp

# Remove the container
docker rm mdsp

# Remove the image
docker rmi mdsp