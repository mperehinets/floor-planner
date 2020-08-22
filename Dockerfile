FROM openjdk:8
ARG JAR_FILE=target/floor-planner.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]
