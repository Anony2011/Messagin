FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} msgapp-0.0.1.jar
ENV JASYPT_ENCRYPTOR_PASSWORD=wrongPassword
ENTRYPOINT ["java","-jar","/msgapp-0.0.1.jar"]