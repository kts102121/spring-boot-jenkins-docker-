FROM ubuntu:16.04

RUN apt-get update && apt-get upgrade -y && apt-get install software-properties-common -y
RUN add-apt-repository ppa:openjdk-r/ppa
RUN apt-get install nginx openjdk-8-jdk gradle -y

COPY ./nginx.conf /etc/nginx

EXPOSE 80

VOLUME /tmp

RUN JASYPT_ENCRYPTOR_PASSWORD=${JASYPT_ENCRYPTOR_PASSWORD} gradle build

ARG DEPENDENCY=build/libs
COPY ${DEPENDENCY}/demo-0.1.0.jar app.jar

ENTRYPOINT sh -c 'service nginx start && java -jar app.jar'