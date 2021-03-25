# Service discovery
Service Discovery is one of the key tenets of a microservice-based architecture. Trying to hand-configure each client or some form of convention can be difficult to do and can be brittle. Eureka is the Netflix Service Discovery Server and Client. The server can be configured and deployed to be highly available, with each server replicating state about the registered services to the others.

## Environment variable
```text
PORT -> Port where application run. Default is 8761
DISCOVER_INSTANCES_HOST -> Instance hostname  of discovery. Default localhost
DISCOVER_AS_CLIENT -> Discovery server can work as client. Default false
FETCH_REGISTRY -> Only clients need to fetch registry. Default false
DISCOVERY_USER -> USer for connecting to the eureka client or server
DISCOVERY_PASSWORD -> Password for connecting to the eureka client or server
CONFIG_URI  -> Config-server uri for receiving application configurations from it
CONFIG_USER -> User is SECURITY_USER from config-server 
CONFIG_PASSWORD -> Password is SECURITY_USER from config-server

```

#### ------
```text

```

## Docker and run

Dockerfiles have the following environment variables:
```text
ENV PROFILE=default
ENV PORT=8761
ENV DISCOVER_INSTANCES_HOST=localhost
ENV DISCOVER_AS_CLIENT=false
ENV FETCH_REGISTRY=false
ENV DISCOVERY_USER=user
ENV DISCOVERY_PASSWORD=password
ENV CONFIG_URI=http://localhost:8888
ENV CONFIG_USER=user
ENV CONFIG_PASSWORD=password
```
!!! In production use sha256 and encrypt DISCOVERY_USER, DISCOVERY_PASSWORD

### Docker run app
To run the application in the dock, follow the steps below:
- Build jar file 
```sh
$ mvn package
```
- Put jar file in same directory with dockerfile
- Build docker image
```sh
$ docker build -t image_name .
```
- Run docker image
```sh
$ docker run --name container_name ^
-e GIT_URI="https://github.com/username/repository_name.git" ^
-e DISCOVERY_USER="username" ^
-e DISCOVERY_PASSWORD="password" ^
-p 8761:8761 ^
-d image_name
```

