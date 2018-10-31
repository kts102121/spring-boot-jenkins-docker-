FROM ubuntu:16.04

RUN apt-get update && apt-get upgrade -y && apt-get install software-properties-common -y
RUN add-apt-repository ppa:openjdk-r/ppa
RUN apt-get install nginx openjdk-8-jdk -y

COPY ./nginx.conf /etc/nginx

EXPOSE 80

VOLUME /tmp
ARG DEPENDENCY=build/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT sh -c 'service nginx start && java -cp app:app/lib/* org.kts1021.docker.demo.DemoApplication'