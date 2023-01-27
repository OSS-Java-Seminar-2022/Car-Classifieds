FROM openjdk:11-jdk
VOLUME /tmp
ADD target/marketour-0.0.1-SNAPSHOT.jar marketour.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/marketour.jar"]