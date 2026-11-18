FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY Portfolio.java .
RUN javac --add-modules jdk.httpserver Portfolio.java

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/Portfolio.class .
EXPOSE 8080
ENTRYPOINT ["java", "--add-modules", "jdk.httpserver", "Portfolio"]
