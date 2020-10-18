FROM openjdk:8-jdk
WORKDIR /usr/src/application
COPY target/*.jar /usr/src/application
VOLUME [ "/logs" ]
ENTRYPOINT [ "java", "-jar","-Dspring.profiles.active=docker","SetTinyUrl-0.0.1-SNAPSHOT.jar" ]
EXPOSE 8102
