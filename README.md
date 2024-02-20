# ICE Track Service
Homework project as part of ICE's recruiting process

Application built with:
- Spring Boot 3.2.2;
- H2 in-memory SQL database;
- Flyway for migrations;
- Swagger for API documentation.

#### Time spent: ~12h.


# Test, Build & Run

I initially started building CRUD endpoints for managing the artists, but ultimately
decided to focus on the given requirements. 
So instead I use DB migration to seed the database. Please use the following artists:

- `abac6bde-2934-4c98-b8b5-68c545b68aea` - Ye (Kanye West);
- `abac6bde-2934-4c98-b8b5-68c545b68aeb` - Michael Jackson.

Moreover, I've seeded the DB with some music genres:

- `bbac6bde-2934-4c98-b8b5-68c545b68aea` - Country;
- `bbac6bde-2934-4c98-b8b5-68c545b68aeb` - Funk;
- `bbac6bde-2934-4c98-b8b5-68c545b68aec` - Samba;
- `bbac6bde-2934-4c98-b8b5-68c545b68aed` - Rock;
- `bbac6bde-2934-4c98-b8b5-68c545b68aee` - Classic;
- `bbac6bde-2934-4c98-b8b5-68c545b68aef` - Hip Hop;
- `bbac6bde-2934-4c98-b8b5-68c545b68ae1` - Pop.

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
(or in `src/test/resources/db/test/migration/` for integration tests)

#### Important: In real life, I would rather use TestContainers for integration tests.<br> Moreover, there is a separate config file for production profile (application-prod.properties). Production DB config should go there (except credentials, that should be passed as environment variables)!


# Requirements

### Add a New Track - ✅
- As a user, you should be able to add a new track to an artist's catalogue,
capturing attributes such as track title, genre, length, etc.
 
### Edit Artist Name - ✅
- As a user, you should be able to edit an artist's name to accommodate instances
where artists have multiple aliases.
 
### Fetch Artist Tracks - ✅
- As a user, you should be able to fetch all tracks associated with a specific artist.

### Artist of the Day - ✅
- As a user, you should be able to see a different "Artist of the Day" in a cyclical
manner on the homepage each day, ensuring a fair rotation through the entire catalogue of artists.
This means if there are n artists, after n days, the cycle restarts with the first artist, ensuring an equal
chance for each artist to be the "Artist of the Day".
#### ***Artist of the day was fully implemented and manually tested, but there are 5 not-yet-implemented integration test cases in `ArtistIT`  