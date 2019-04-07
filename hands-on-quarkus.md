autoscale: true
footer: // Hands on Quarkis.io -> { created with :heart: and :coffee: by @LeanderReimer @qaware }
slidenumbers: true

[.hide-footer]
[.slidenumbers: false]
# __Hands on__ Quarkus.io

---

## mvn package -Pnative -Dnative-image.docker-build=true

```
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
