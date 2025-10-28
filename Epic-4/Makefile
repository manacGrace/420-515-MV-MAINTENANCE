# Makefile for Series Recommendation Platform - Docker Compose Setup
# Variables
COMPOSE_FILE = docker-compose.yml
PROFILE_APP = --profile app
PROFILE_PROD = --profile production

# Default target
.PHONY: help
help:
	@echo "Available commands:"
	@echo "  start      - Start core services (Jenkins + Database)"
	@echo "  start-app  - Start full application stack (Backend + Frontend + Database + Jenkins)"
	@echo "  start-prod - Start production setup with Nginx"
	@echo "  build      - Build all services"
	@echo "  build-app  - Build application services (Backend + Frontend)"
	@echo "  stop       - Stop all services"
	@echo "  restart    - Restart all services"
	@echo "  logs       - Show logs for all services"
	@echo "  logs-backend - Show backend logs"
	@echo "  logs-frontend - Show frontend logs"
	@echo "  logs-db    - Show database logs"
	@echo "  logs-jenkins - Show Jenkins logs"
	@echo "  clean      - Stop and remove all containers, networks, and volumes"
	@echo "  status     - Show status of all services"
	@echo "  test       - Run backend tests"
	@echo "  dev-backend - Start backend for local development"
	@echo "  dev-frontend - Start frontend for local development"

# Start core services (Jenkins + Database)
.PHONY: start
start:
	@echo "Starting core services (Jenkins + Database)..."
	docker-compose up -d
	@echo "Core services started"
	@echo "Jenkins: http://localhost:8080"
	@echo "Database: localhost:3306"

# Start full application stack
.PHONY: start-app
start-app:
	@echo "Starting full application stack..."
	docker-compose $(PROFILE_APP) up -d
	@echo "Application stack started"
	@echo "Frontend: http://localhost:5173"
	@echo "Backend API: http://localhost:8888/api/series/all"
	@echo "Jenkins: http://localhost:8080"
	@echo "Database: localhost:3306"

# Start production setup
.PHONY: start-prod
start-prod:
	@echo "Starting production setup with Nginx..."
	docker-compose $(PROFILE_APP) $(PROFILE_PROD) up -d
	@echo "Production setup started"
	@echo "Application: http://localhost:80"
	@echo "Jenkins: http://localhost:8080"

# Build all services
.PHONY: build
build:
	@echo "Building all services..."
	docker-compose $(PROFILE_APP) build
	@echo "All services built"

# Build application services
.PHONY: build-app
build-app:
	@echo "Building application services..."
	docker-compose $(PROFILE_APP) build series-backend series-frontend
	@echo "Application services built"

# Stop all services
.PHONY: stop
stop:
	@echo "Stopping all services..."
	docker-compose down
	@echo "All services stopped"

# Restart all services
.PHONY: restart
restart: stop start-app
	@echo "All services restarted"

# Show logs for all services
.PHONY: logs
logs:
	@echo "Showing logs for all services..."
	docker-compose logs -f

# Show backend logs
.PHONY: logs-backend
logs-backend:
	@echo "Showing backend logs..."
	docker-compose logs -f series-backend

# Show frontend logs
.PHONY: logs-frontend
logs-frontend:
	@echo "Showing frontend logs..."
	docker-compose logs -f series-frontend

# Show database logs
.PHONY: logs-db
logs-db:
	@echo "Showing database logs..."
	docker-compose logs -f mariadb

# Show Jenkins logs
.PHONY: logs-jenkins
logs-jenkins:
	@echo "Showing Jenkins logs..."
	docker-compose logs -f jenkins

# Clean up everything
.PHONY: clean
clean:
	@echo "Cleaning up all containers, networks, and volumes..."
	docker-compose down -v --remove-orphans
	docker system prune -f
	@echo "Cleanup completed"

# Show status of all services
.PHONY: status
status:
	@echo "Service status:"
	docker-compose ps

# Run backend tests
.PHONY: test
test:
	@echo "Running backend tests..."
	docker-compose exec series-backend ./mvnw clean test
	@echo "Tests completed"

# Start backend for local development
.PHONY: dev-backend
dev-backend:
	@echo "Starting backend for local development..."
	cd ../420-515-MV-MAINTENANCE-DE-LOGICIEL-LAB1-BACKEND/backend && ./mvnw spring-boot:run

# Start frontend for local development
.PHONY: dev-frontend
dev-frontend:
	@echo "Starting frontend for local development..."
	cd ../420-515-MV-MAINTENANCE-DE-LOGICIEL-LAB1-FRONTEND && npm run dev

# Generate JavaDoc
.PHONY: javadoc
javadoc:
	@echo "Generating JavaDoc..."
	docker-compose exec series-backend ./mvnw javadoc:javadoc
	@echo "JavaDoc generated in target/site/apidocs"

# Generate JaCoCo report
.PHONY: jacoco
jacoco:
	@echo "Generating JaCoCo report..."
	docker-compose exec series-backend ./mvnw jacoco:report
	@echo "JaCoCo report generated in target/site/jacoco"

# Quick development setup
.PHONY: dev-setup
dev-setup: start-app
	@echo "Development setup complete!"
	@echo "Frontend: http://localhost:5173"
	@echo "Backend API: http://localhost:8888/api/series/all"
	@echo "Jenkins: http://localhost:8080"