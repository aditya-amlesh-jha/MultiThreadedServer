# Multi-Threaded Web Server

This is implementation of server which uses a fixed thread pool to serve the incoming requests from clients

## Setup

Navigate to ./src/main/resources and edit config.properties

```
thread.pool.size=no_of_threads
server.port=server_port 
server.timeout.milliseconds=server_timeout_in_milliseconds
```
eg ( Multi-Threaded )

```
thread.pool.size=10
server.port=8000
server.timeout.milliseconds=30000
```

eg ( Single-Threaded )

```
thread.pool.size=1
server.port=8000
server.timeout.milliseconds=30000
```

## Design Pattern
- Config and handler are implemented using singleton design pattern

- Web server implements utilizes command pattern to serve requests by passing the socket connection to handler which takes a thread from thread pool and serves the request

## Technology
Language : Java

Libraries : Socket, Thread, BufferedReader, PrintWriter, Executor Service

