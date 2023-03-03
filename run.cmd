@echo OFF

REM Build the Docker image
docker build -t mdsp .

REM Run the container and execute the tests
docker run --name mdsp -it mdsp mvn test

REM Stop the container
docker stop mdsp

REM Remove the container
docker rm mdsp

REM Remove the image
docker rmi mdsp