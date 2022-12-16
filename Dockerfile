FROM openjdk:17-ea-10-jdk-alpine
COPY target/ebank-2.jar ebank-2.jar
ENTRYPOINT ["java","-jar","ebank-2.jar"]
