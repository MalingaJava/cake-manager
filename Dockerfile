FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=target/cake-manager.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","cake-manager.jar"]