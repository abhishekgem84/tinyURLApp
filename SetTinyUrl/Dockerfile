FROM openjdk:8-jdk
#RUN apt-get update
#RUN apt-get install net-tools
#RUN apt-get install telnet
WORKDIR /usr/src/application
COPY target/*.jar /usr/src/application
VOLUME [ "/logs" ]
ENTRYPOINT [ "java", "-jar","-Dspring.profiles.active=docker","SetTinyUrl-0.0.1-SNAPSHOT.jar" ]
EXPOSE 8102
