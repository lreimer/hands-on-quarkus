autoscale: true
footer: // Hands on _Quarkus_ -> { created with :heart: and :coffee: by _@LeanderReimer @qaware_ }
slidenumbers: true

[.hide-footer]
[.slidenumbers: false]
# __Hands on__
# [fit] _Quarkus_

---

## [fit] Quarkus is the New yService Framework on the Block

- Spark, Javalin, Helidon, Micronaut. Quarkus.
- Supersonic, Subatomic Java. Kubernetes Native Java stack tailored for GraalVM.
- Best of breed libraries and standards: CDI, JPA, JAX-RS, MicroProfile, Netty, Kafka, Infinispan, ...
- Developer Experience: 12-factors, easy configuration, live reload, build tool integration, testing, docs, ...
- Apache v2, Frequent Releases, 39 Bugs, IBM (Red Hat), Known Contributors

---

## Container and Microservice First

![inline](https://quarkus.io/assets/images/quarkus_graphics_v3_bootmem_wide_03.png)

Low Memory requirements, especially when used with Graal Native.
Good performance. Fast boot and startup. But lower overall throughput!

---

## Good Developer Experience

```
$ mvn io.quarkus:quarkus-maven-plugin:0.12.0:create \
    -DprojectGroupId=cloud.native.quarkus \
    -DprojectArtifactId=hands-on-quarkus \
    -DclassName="cloud.native.quarkus.LibraryResource" \
    -Dpath="/library"

$ mvn compile quarkus:dev
```

The Maven Plugin is pretty complete. Gradle plugin still needs work.
_Micronaut provides a CLI, Helidon uses Maven Archetype._
Dev Mode performs live-reload of changes. Well, it's actually a live-restart!

---

```
$ mvn quarkus:list-extensions

[INFO] --- quarkus-maven-plugin:0.12.0:list-extensions (default-cli) @ hands-on-quarkus ---
[INFO] Available extensions:
[INFO] 	 * Agroal - Database connection pool (io.quarkus:quarkus-agroal)
[INFO] 	 * Arc (io.quarkus:quarkus-arc)
[INFO] 	 * AWS Lambda (io.quarkus:quarkus-amazon-lambda)
[INFO] 	 * Camel Core (io.quarkus:quarkus-camel-core)
[INFO] 	 * Camel Infinispan (io.quarkus:quarkus-camel-infinispan)
[INFO] 	 * Camel Netty4 HTTP (io.quarkus:quarkus-camel-netty4-http)
[INFO] 	 * Camel Salesforce (io.quarkus:quarkus-camel-salesforce)
[INFO] 	 * Eclipse Vert.x (io.quarkus:quarkus-vertx)
[INFO] 	 * Hibernate ORM (io.quarkus:quarkus-hibernate-orm)
[INFO] 	 * Hibernate ORM with Panache (io.quarkus:quarkus-hibernate-orm-panache)
[INFO] 	 * Hibernate Validator (io.quarkus:quarkus-hibernate-validator)
[INFO] 	 * Infinispan Client (io.quarkus:quarkus-infinispan-client)
[INFO] 	 * JDBC Driver - ***** (io.quarkus:quarkus-jdbc-*****) (H2, MariaDB, PostgreSQL)
[INFO] 	 * JSON-B (io.quarkus:quarkus-jsonb)
[INFO] 	 * JSON-P (io.quarkus:quarkus-jsonp)
[INFO] 	 * Kotlin (io.quarkus:quarkus-kotlin)
[INFO] 	 * Narayana JTA - Transaction manager (io.quarkus:quarkus-narayana-jta)
[INFO] 	 * RESTEasy (io.quarkus:quarkus-resteasy)
[INFO] 	 * RESTEasy - JSON-B (io.quarkus:quarkus-resteasy-jsonb)
[INFO] 	 * Scheduler (io.quarkus:quarkus-scheduler)
[INFO] 	 * Security (io.quarkus:quarkus-elytron-security)
[INFO] 	 * SmallRye ***** (io.quarkus:quarkus-smallrye-*****)
[INFO] 	 * Spring DI compatibility layer (io.quarkus:quarkus-spring-di)
[INFO] 	 * Undertow (io.quarkus:quarkus-undertow)
[INFO] 	 * Undertow WebSockets (io.quarkus:quarkus-undertow-websockets)

$ mvn quarkus:add-extension -Dextensions="io.quarkus:quarkus-jsonb"
```

---

## [fit] Building the native image takes a long time!

```
$ mvn package -Pnative -Dnative-image.docker-build=true

[hands-on-quarkus-1.0-SNAPSHOT-runner:6]    classlist:  56,163.93 ms
[hands-on-quarkus-1.0-SNAPSHOT-runner:6]        (cap):   2,531.05 ms
[hands-on-quarkus-1.0-SNAPSHOT-runner:6]        setup:   6,214.21 ms
...
[hands-on-quarkus-1.0-SNAPSHOT-runner:6]   (typeflow): 170,341.22 ms
[hands-on-quarkus-1.0-SNAPSHOT-runner:6]    (objects):  61,226.10 ms
[hands-on-quarkus-1.0-SNAPSHOT-runner:6]   (features):   2,895.15 ms
[hands-on-quarkus-1.0-SNAPSHOT-runner:6]     analysis: 245,153.93 ms
...
[hands-on-quarkus-1.0-SNAPSHOT-runner:6]     universe:  13,229.29 ms
[hands-on-quarkus-1.0-SNAPSHOT-runner:6]      (parse):  93,961.45 ms
[hands-on-quarkus-1.0-SNAPSHOT-runner:6]     (inline): 109,248.12 ms
[hands-on-quarkus-1.0-SNAPSHOT-runner:6]    (compile): 254,735.79 ms
[hands-on-quarkus-1.0-SNAPSHOT-runner:6]      compile: 471,609.18 ms
[hands-on-quarkus-1.0-SNAPSHOT-runner:6]        image:  22,405.24 ms
[hands-on-quarkus-1.0-SNAPSHOT-runner:6]        write:   6,450.14 ms
[hands-on-quarkus-1.0-SNAPSHOT-runner:6]      [total]: 930,591.13 ms
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  15:52 min
[INFO] Finished at: 2019-04-06T15:56:39+02:00
[INFO] ------------------------------------------------------------------------
```

---

## Quarkus has limitations

- Not every feature of CDI is supported.
  - simplified, only one bean archive with annotated bean discovery
  - private members and field level injection are not encouraged
  - decorators and specialization are not supported
  - no support for portable extensions
- using an open source library not available as Quarkus extension may prove difficult

---

# [fit] _Demo_
