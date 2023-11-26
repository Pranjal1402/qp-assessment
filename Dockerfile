# Use an OpenJDK runtime as a base image
FROM openjdk:17-oracle

# Set the working directory inside the container
WORKDIR /app

COPY target/ApiAssignment-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 3000

ENV SPRING_DATASOURCE_URL=jdbc:sqlserver://localhost:1433/questionpro
ENV SPRING_DATASOURCE_USERNAME=sa
ENV SPRING_DATASOURCE_PASSWORD=Pranjal123

CMD ["java", "-jar", "app.jar"]
