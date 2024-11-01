FROM openjdk:17-jdk

EXPOSE 8080

COPY ./target/ems-*.jar /usr/app/
WORKDIR /usr/app

CMD java -jar ems-*.jar
