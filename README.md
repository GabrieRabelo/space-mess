[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=GabrieRabelo_space-mess&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=GabrieRabelo_space-mess)

# Space Mess

The problem:
I received a task idea from business team. Something like that on a paper:

```
Planet's Area : 5x5

Probe 1 land position: x=1, y=2 with direction to north
Commands sequence: LMLMLMLMM
Final probe position: x=1 y=3 with direction to north

Probe 2 land position : x=3, y=3 with direction to east
Commands sequence: MMRMMRMRRML
Final probe position: x=5 y=1 with direction to north
```

So I thought...

![my modeling](https://i.imgur.com/vrNUELR.png)

## How does that works?

On this application, it's possible to create a planet to register its size and also create a 
probe to control.

### Creating a planet
The route `POST /planets` exists to create a planet, and it expects a height and width of initial area.\
Example payload:
```json
{
  "height": 0,
  "width": 0
}
```

### Creating a probe
To create a probe the route `POST /probes` is available without any payload.

### Landing a probe
In order to land a probe into a planet the route is `PATCH /probes/{id}/land` where {id} stands for id of a previous created probe.

Example payload:
```json
{
  "planetId":2,
  "x": 3,
  "y": 3,
  "direction": "NORTH"
}
```

### Moving a probe
At least, the route `PATCH /probes/{id}/command` accepts a command to send to a landed probe.

Example payload:
```json
{
  "command": "LMRRRMLR"
}
```

## Stack

* Programming Language: Kotlin
* Framework: Spring Boot
* Dependencies Manager: Gradle
* Embedded database: H2 

## How to run

To run this application, we need to run the following gradle command:

`./gradlew clean bootrun`

## Documentation
After running the application, the route 
`http://localhost:8080/swagger-ui/index.html#/` is used to access the live documentation (OpenAPI).

![swagger](https://i.imgur.com/0FHdnyj.png)