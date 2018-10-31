FROM ubuntu:16.04

RUN apt-get update && apt-get upgrade -y && apt-get install software-properties-common -y
RUN add-apt-repository ppa:openjdk-r/ppa
RUN apt-get install nginx openjdk-8-jdk -y

COPY ./nginx.conf /etc/nginx

EXPOSE 80

VOLUME /tmp
ARG DEPENDENCY=build/libs
COPY ${DEPENDENCY}/demo-0.1.0.jar app.jar

ENTRYPOINT sh -c 'service nginx start && java -jar app.jar'