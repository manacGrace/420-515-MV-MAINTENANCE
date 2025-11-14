# Simple Dockerfile for Spring Boot Backend
FROM eclipse-temurin:21-jdk

# Install Maven
RUN apt-get update && apt-get install -y maven && rm -rf /var/lib/apt/lists/*

# Set working directory
WORKDIR /app

# Copy pom.xml first for better caching
COPY 420-515-MV-MAINTENANCE-DE-LOGICIEL-LAB1-BACKEND/backend/pom.xml ./

# Download dependencies
RUN mvn dependency:go-offline -B

# Copy source code
COPY 420-515-MV-MAINTENANCE-DE-LOGICIEL-LAB1-BACKEND/backend/src ./src

# Build the application
RUN mvn clean package -DskipTests

# Debug: List what was built
RUN ls -la target/ && find target/ -name "*.jar" -type f

# Expose port
EXPOSE 8888

# Run the application
CMD ["java", "-jar", "target/backend-0.0.1-SNAPSHOT.jar"]
