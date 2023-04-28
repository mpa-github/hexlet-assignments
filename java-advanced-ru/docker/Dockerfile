FROM gradle:7.4.0-jdk17
WORKDIR /app
COPY ./app .
RUN gradle installDist
EXPOSE 3000
ENTRYPOINT ["build/install/simple-application/bin/simple-application"]
