FROM gcr.io/google_appengine/openjdk8
ADD ./target/collabeditor-server.jar /colabeditor/server.jar

ENTRYPOINT ["java", "-jar", "/collabeditor/server.jar"]