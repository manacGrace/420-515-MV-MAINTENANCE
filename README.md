# Series Recommendation Platform - Docker Setup

## Overview

Complete containerized application stack with Spring Boot backend, React frontend, MariaDB database, and Jenkins CI/CD pipeline.

## 🚀 Quick Start

### Prerequisites
- Docker Engine 20.10+
- Docker Compose 2.0+

### Start Everything

```bash
# Navigate to Epic-4 directory
cd Epic-4

# Start full application stack (Backend + Frontend + Database + Jenkins)
docker-compose --profile app up -d

# Or use Makefile commands (if make is available)
make start-app
```

### Access Points
- **Frontend**: http://localhost:5173
- **Backend API**: http://localhost:8888/api/series/all
- **Jenkins**: http://localhost:8080
- **Database**: localhost:3306

## 🔧 Makefile Commands

### Essential Commands

```bash
# Start core services (Jenkins + Database only)
make start

# Start full application stack (Backend + Frontend + Database + Jenkins)
make start-app

# Start production setup with Nginx
make start-prod

# Build all services
make build

# Stop all services
make stop

# Restart all services
make restart

# Show logs
make logs
make logs-backend
make logs-frontend
make logs-jenkins

# Show service status
make status

# Clean up everything
make clean
```

### Development Commands

```bash
# Run backend tests
make test

# Start backend for local development
make dev-backend

# Start frontend for local development
make dev-frontend

# Quick development setup
make dev-setup
```

## 🔄 Jenkins Setup (First Time Only)

### Initial Configuration

1. **Start Jenkins**:
   ```bash
   docker-compose up -d jenkins
   # OR
   make start
   ```

2. **Get Admin Password**:
   ```bash
   docker exec series-jenkins cat /var/jenkins_home/secrets/initialAdminPassword
   ```

3. **Access Jenkins**: http://localhost:8080

4. **Initial Setup**:
   - Paste the admin password from step 2
   - Click "Install suggested plugins"
   - Wait for plugin installation (5-10 minutes)
   - Create admin user account
   - Configure Jenkins URL (keep default: http://localhost:8080)

### Create Pipeline Job

1. **New Item** → **Pipeline** → Name: `series-backend-pipeline`

2. **Pipeline Configuration**:
   - Pipeline script: From SCM
   - SCM: Git
   - Repository URL: Your repository URL
   - Script path: `Epic-4/Jenkinsfile`

3. **Build Now** to test the pipeline

## 🐳 Docker Compose Commands

### Service Management

```bash
# Start specific services
docker-compose up -d mariadb jenkins
docker-compose --profile app up -d series-backend series-frontend

# View service status
docker-compose ps

# View logs
docker-compose logs series-backend
docker-compose logs series-frontend
docker-compose logs mariadb
docker-compose logs jenkins

# Restart services
docker-compose restart series-backend
docker-compose restart series-frontend

# Stop services
docker-compose down
docker-compose --profile app down
```

### Build Commands

```bash
# Build and start (first time or after code changes)
docker-compose --profile app up -d --build

# Build specific service
docker-compose --profile app build series-backend
docker-compose --profile app build series-frontend
```

## 🚨 Troubleshooting
1. **Services Not Starting**:
   ```bash
   # Check logs
   docker-compose logs series-backend
   docker-compose logs series-frontend
   
   # Restart services
   docker-compose restart series-backend
   docker-compose restart series-frontend
   ```

2. **Database Connection Issues**:
   ```bash
   # Check database logs
   docker-compose logs mariadb
   
   # Restart database
   docker-compose restart mariadb
   
   # Test connection
   docker exec series-mariadb mysql -u appuser -papppassword -e "SHOW DATABASES;"
   ```

4. **Jenkins Issues**:
   ```bash
   # Check Jenkins logs
   docker-compose logs jenkins
   
   # Restart Jenkins
   docker-compose restart jenkins
   
   # Get admin password again
   docker exec series-jenkins cat /var/jenkins_home/secrets/initialAdminPassword
   ```

### Clean Up

```bash
# Stop and remove all containers, networks, and volumes
docker-compose down -v --remove-orphans

# Clean Docker system
docker system prune -f

# Or use Makefile
make clean
```

## 🔧 Development

### Local Development (Without Docker)
Change user and password in application.properties
```bash
# Backend
cd ../420-515-MV-MAINTENANCE-DE-LOGICIEL-LAB1-BACKEND/backend
./mvnw spring-boot:run

# Frontend
cd ../420-515-MV-MAINTENANCE-DE-LOGICIEL-LAB1-FRONTEND
npm install
npm run dev
```

### Testing

```bash
# Run backend tests
make test

# Or manually
docker-compose exec series-backend ./mvnw clean test
```
