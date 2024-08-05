# SportEvents-Application

# Sport Event Management System

This is a Spring Boot project for managing sport events. The project includes the creation of a database with multiple entities, the implementation of all CRUD operations, and the creation of a REST Web API Server.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
- [API Endpoints](#api-endpoints)
- [Database Schema](#database-schema)


## Introduction

The Sport Event Management System allows users to manage sport events, attendees, and locations. The project demonstrates the use of Spring Boot for building a RESTful web service, integrating a relational database, and handling CRUD operations.

## Features

- Manage sport events
- Manage attendees
- Manage event locations
- Create, Read, Update, and Delete operations for all entities
- One-to-many and many-to-many relationships

## Technologies Used

- Java
- Spring Boot
- Hibernate (JPA)
- MySQL (or any other relational database)
- Maven
- RESTful API

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven
- MySQL (or any other relational database)

### Setup

1. **Clone the repository**

   ```bash
   git clone https://github.com/hatim45/sport-event-management.git
   cd sport-event-management
   ```

2. **Configure the database**

   Update the `application.properties` file with your database configurations:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/sport_event_db
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   spring.jpa.hibernate.ddl-auto=update
   ```

3. **Build the project**

   ```bash
   mvn clean install
   ```

4. **Run the project**

   ```bash
   mvn spring-boot:run
   ```

## API Endpoints

### Attendees

- `GET /api/attendees` - Get all attendees
- `GET /api/attendees/{id}` - Get attendee by ID
- `POST /api/attendees` - Create a new attendee
- `PUT /api/attendees/{id}` - Update an attendee
- `DELETE /api/attendees/{id}` - Delete an attendee

### Locations

- `GET /api/locations` - Get all locations
- `GET /api/locations/{id}` - Get location by ID
- `POST /api/locations` - Create a new location
- `PUT /api/locations/{id}` - Update a location
- `DELETE /api/locations/{id}` - Delete a location

### Sport Events

- `GET /api/events` - Get all sport events
- `GET /api/events/{id}` - Get sport event by ID
- `POST /api/events` - Create a new sport event
- `PUT /api/events/{id}` - Update a sport event
- `DELETE /api/events/{id}` - Delete a sport event

## Database Schema

### Attendee

| Field         | Type    |
|---------------|---------|
| attendeeId    | Long    |
| name          | String  |
| email         | String  |

### Location

| Field         | Type    |
|---------------|---------|
| locationId    | Long    |
| locationName  | String  |

### SportEvent

| Field         | Type    |
|---------------|---------|
| eventId       | Long    |
| eventName     | String  |
| date          | Date    |

### SportEventAttendee

| Field         | Type    |
|---------------|---------|
| attendeeId    | Long    |
| attendeeName  | String  |
| attendeeEmail | String  |

### SportEventLocation

| Field         | Type    |
|---------------|---------|
| locationId    | Long    |
| locationName  | String  |



