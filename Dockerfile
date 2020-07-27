FROM gcr.io/google_appengine/jetty9
VOLUME /tmp
ADD ./demo-0.0.1-SNAPSHOT.jar /app.jar
CMD java -jar /app.jar