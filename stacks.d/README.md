# stacks.d

this directory contains stacks

## usage

### configuration

each stack's [.env](iceburg-local/.env) file provides defaults, such as what port numbers a service binds to. you can override values by providing them in your environment, e.g.

```
LOCAL_REDIS_PORT=6888 \
LOCAL_DB_PORT=5555 \
  docker-compose ...
```

once a stack is running (see [docker-compose](#docker-compose)), be sure to configure your application services to use it. see the README.md file in the stack folder for details.


### docker-compose

[docker-compose](https://docs.docker.com/compose/compose-file/) is used to start, stop, and view logs of stack services. **be sure you have it installed**.


> :bulb: run all commands from the stack directory, e.g.

```
cd stacks.d/iceburg-local
```

##### start stack in foreground

```
docker-compose up
```

service logs are printed to stdout. ctrl-c will exit all services. data will be persisted in `data/`.

##### stop stack and remove volumes

```
docker-compose down -v
```

cleans up a stack and makes sure services are no longer running.

> :bulb: this will free up ports used by services on your machine


##### start stack in background

```
docker-compose up -d
```

you can use the logs command to view logs of all services, or individual services. e.g.

```
docker-compose logs
docker-compose logs -f minio
```
