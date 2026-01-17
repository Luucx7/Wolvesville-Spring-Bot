FROM eclipse-temurin:19-jdk AS build
WORKDIR /app

RUN apt-get update && apt-get install -y maven && rm -rf /var/lib/apt/lists/*

COPY pom.xml .
RUN mvn -q -DskipTests dependency:go-offline

COPY . .
RUN mvn -q -DskipTests package

FROM eclipse-temurin:19-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]
