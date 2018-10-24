FROM openjdk:10-jre

ADD ./target/MovieCruiserApp-0.0.1-SNAPSHOT.jar /usr/app/MovieCruiserApp-0.0.1-SNAPSHOT.jar

WORKDIR usr/app

ENTRYPOINT ["java","-jar", "MovieCruiserApp-0.0.1-SNAPSHOT.jar"]


