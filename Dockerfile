FROM tomcat:9.0-jdk11

# Remove default apps
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy your WAR as ROOT app
COPY target/ROOT.war /usr/local/tomcat/webapps/ROOT.war

# Railway uses PORT env variable
ENV PORT=8080
EXPOSE 8080

CMD ["catalina.sh", "run"]