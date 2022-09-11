FROM registry.access.redhat.com/ubi8/openjdk-11:latest
COPY build/libs/Customer-MS-0.0.1-SNAPSHOT.jar  /app/
WORKDIR /app/

ENTRYPOINT ["java"]
CMD ["-Xms64m", "-Xmx512m", "-jar", "Customer-MS-0.0.1-SNAPSHOT.jar"]
