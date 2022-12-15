FROM openjdk:17-ea-10-jdk-alpine
COPY target/ebank-1.jar ebank-1.jar
ENTRYPOINT ["java","-jar","ebank-1.jar"]
