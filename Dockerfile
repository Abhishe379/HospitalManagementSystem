# Step 1: Build Stage
FROM eclipse-temurin:22-jdk-jammy AS build
WORKDIR /app
COPY . .
RUN chmod +x gradlew

RUN ./gradlew clean build -x test --no-daemon


# Step 2: Run Stage
FROM eclipse-temurin:22-jre-jammy
WORKDIR /app
EXPOSE 8080
COPY --from=build /app/build/libs/HospitalManagementSystem-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
