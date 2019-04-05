# Hands-on Quarkus

Hands-on on Quarkus.io demo repository for a enterprise ready library showcase service.
The README contains the basic instructions and commands that I used to create the project.

## Bootstrapping

Make sure you have read the prerequisites in https://quarkus.io/guides/getting-started-guide
Quarkus currently supports Maven and Gradle as build tools. The Maven plugin currently offers
more features, so we are going to use this one.

In order to create this project I issued the following command:
```
mvn io.quarkus:quarkus-maven-plugin:0.12.0:create \
    -DprojectGroupId=cloud.native.quarkus \
    -DprojectArtifactId=hands-on-quarkus \
    -DclassName="cloud.native.quarkus.LibraryResource" \
    -Dpath="/library"
```

To develop and run the servie continuously, use `mvn compile quarkus:dev`. To build
it, use `mvn package`. 

If you want to build a binary executable then you need Graal VM and call `mvn package -Pnative`. This takes quite some time so grab a :coffee:


## Extensions

```
mvn compile quarkus:dev -Debug=true

mvn quarkus:list-extensions

mvn quarkus:add-extension -Dextensions="io.quarkus:quarkus-hibernate-orm-panache"
mvn quarkus:add-extension -Dextensions="io.quarkus:quarkus-jdbc-postgresql"
mvn quarkus:add-extension -Dextensions="io.quarkus:quarkus-resteasy-jsonb"
mvn quarkus:add-extension -Dextensions="io.quarkus:quarkus-hibernate-validator"

mvn quarkus:add-extension -Dextensions="io.quarkus:quarkus-smallrye-fault-tolerance"
mvn quarkus:add-extension -Dextensions="io.quarkus:quarkus-smallrye-health"
mvn quarkus:add-extension -Dextensions="io.quarkus:quarkus-smallrye-metrics"
mvn quarkus:add-extension -Dextensions="io.quarkus:quarkus-smallrye-openapi"
```

## Containerization

The containerization can be done on two ways: as normal JVM based application and as a
natively compiled binary. The `Dockerfile` sources are located in `src/main/docker`.

Use the following command to build a normal JVM image and run it:
```
docker build -f src/main/docker/Dockerfile.jvm -t hands-on-quarkus:jvm .
docker run -i --rm -p 8080:8080 hands-on-quarkus:jvm
```

To build the native binary image and make it even smaller, use the following Dockerfile and put it into `src/main/docker/Dockerfile.native`.
```
FROM cescoffier/native-base:latest
WORKDIR /work/
COPY target/*-runner /work/application
EXPOSE 8080
CMD ["./application", "-Dquarkus.http.host=0.0.0.0"]
```

Then use the following command to build the native image and run it:
```
docker build -f src/main/docker/Dockerfile.native -t hands-on-quarkus:native .
docker run -i --rm -p 8080:8080 hands-on-quarkus:native
```
