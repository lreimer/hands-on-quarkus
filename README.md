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
