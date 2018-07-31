FROM anapsix/alpine-java
VOLUME /tmp
COPY parameter-api.jar app.jar
EXPOSE 8080
ENTRYPOINT exec java -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=$ENVIRONMENT -Duser.timezone=Europe/Madrid $JAVA_OPTS -jar /app.jar
