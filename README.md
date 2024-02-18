# ICE Track Service
Homework project as part of ICE's recruiting process

Application built with:
- Spring Boot 3.2.2;
- H2 in-memory SQL database;
- Flyway for migrations;
- Swagger for API documentation.


# Test, Build & Run

TBD

### Step #1 - Build fat jar
Built with gradle. In the root folder, run the following command:

`./gradlew clean build`

This will run all the build steps, and should generate the fat jar under `build/libs/track-service-0.0.1-SNAPSHOT.jar`

### Step #2 - Build docker image
Simply run:

`docker build -t ice-track-service .`

### Step #3 - Run docker image

Run the following command:

`docker run -p 8080:8080 ice-track-service`

### Docs

Swagger API documentation available under

[http://localhost:8080/swagger](http://localhost:8080/swagger).

### Database

H2 provides a nice web interface to navigate its schemas/tables: 

[http://localhost:8080/h2-console](http://localhost:8080/h2-console).

Use the following settings to login:
```
Driver Class: org.h2.Driver
JDBC URL: jdbc:h2:mem:trackservice
User Name: ice
Password: [leave it empty]
```

Flyway runs the db migrations during app's startup.
The migration files can be found in `src/main/resources/db/migration/`


# Requirements

### Add a New Track
- As a user, you should be able to add a new track to an artist's catalogue,
capturing attributes such as track title, genre, length, etc.
 
### Edit Artist Name
- As a user, you should be able to edit an artist's name to accommodate instances
where artists have multiple aliases.
 
### Fetch Artist Tracks
- As a user, you should be able to fetch all tracks associated with a specific artist.

### Artist of the Day
- As a user, you should be able to see a different "Artist of the Day" in a cyclical
manner on the homepage each day, ensuring a fair rotation through the entire catalogue of artists.
This means if there are n artists, after n days, the cycle restarts with the first artist, ensuring an equal
chance for each artist to be the "Artist of the Day".