autoscale: true
footer: // Hands on Graal VM and Quarkis.io -> { created with :heart: and :coffee: by @LeanderReimer @qaware }
slidenumbers: true

[.hide-footer]
[.slidenumbers: false]
# __Hands on__ GraalVM __and__ Quarkus.io

---

## mvn package -Pnative

```
[hands-on-quarkus-1.0-SNAPSHOT-runner:3171]    classlist:  17,120.00 ms
[hands-on-quarkus-1.0-SNAPSHOT-runner:3171]        (cap):   5,721.20 ms
[hands-on-quarkus-1.0-SNAPSHOT-runner:3171]        setup:   7,886.57 ms
...
[hands-on-quarkus-1.0-SNAPSHOT-runner:3171]   (typeflow):  89,248.05 ms
[hands-on-quarkus-1.0-SNAPSHOT-runner:3171]    (objects):  50,545.10 ms
[hands-on-quarkus-1.0-SNAPSHOT-runner:3171]   (features):   1,733.76 ms
[hands-on-quarkus-1.0-SNAPSHOT-runner:3171]     analysis: 148,026.01 ms
...
[hands-on-quarkus-1.0-SNAPSHOT-runner:3171]     universe:   3,431.47 ms
[hands-on-quarkus-1.0-SNAPSHOT-runner:3171]      (parse):  15,240.14 ms
[hands-on-quarkus-1.0-SNAPSHOT-runner:3171]     (inline):   9,607.26 ms
[hands-on-quarkus-1.0-SNAPSHOT-runner:3171]    (compile): 102,017.88 ms
[hands-on-quarkus-1.0-SNAPSHOT-runner:3171]      compile: 132,731.39 ms
[hands-on-quarkus-1.0-SNAPSHOT-runner:3171]        image:  13,031.90 ms
[hands-on-quarkus-1.0-SNAPSHOT-runner:3171]        write:   5,020.46 ms
[hands-on-quarkus-1.0-SNAPSHOT-runner:3171]      [total]: 359,314.91 ms
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  06:24 min
[INFO] Finished at: 2019-04-05T17:00:48+02:00
[INFO] ------------------------------------------------------------------------
```
