# OpenAPI Specification Generator PoC

This is a proof of concept to generate OpenAPI specification from Akka HTTP routes.

## Short Description  
An Akka HTTP Server is running with multiple routes.

The endpoints are defined with Tapir.

The OpenAPI specification is generated from the Tapir endpoints.

The OpenAPI specification is served on the `/openapi.json` endpoint. 


## Run the project

```shell
sbt compile 
sbt run
```

## Links:

- [Tapir Documentation](https://tapir.softwaremill.com/en/latest/)
- [Tapir Github](https://github.com/softwaremill/tapir)
