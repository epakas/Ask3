FROM adoptopenjdk/openjdk13-openj9:latest
MAINTAINER ece.upatras.gr
COPY deploy/gr.upatras.mqtt-0.0.1-SNAPSHOT.jar /opt/gr.upatras.mqtt/
WORKDIR /opt/gr.upatras.mqtt/
CMD ["java", "-jar", "/opt/gr.upatras.mqtt/gr.upatras.mqtt-0.0.1-SNAPSHOT.jar"]
EXPOSE 8888