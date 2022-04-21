# MangaJJ - Controling my collection

Project created to control my magazines' collection.

## Steps

- [x] Integrate with some API
    - Lazy load process
- [x] Create a job to update my database
- [ ] Add authentication
- [x] Pagination
- [x] Swagger
- [x] Deploy
    - Heroku

## Technology

- [Java Spring Boot](https://spring.io/projects/spring-boot)
- [Hibernate](https://hibernate.org/)
- [MySQL](https://www.mysql.com/)
- [H2 Database](https://www.h2database.com/html/main.html)
- [Docker](https://www.docker.com/)
- [JUnit](https://junit.org/junit5/)

### Config Database

Access the file **[application.properties](src/main/resources/application.yml)**

```
spring.datasource.username={username}
spring.datasource.password={password}
```

### Integrations

Using external API to populate database: https://github.com/jikan-me/jikan.

Using service [MangaBit](https://github.com/franproque/MangaBit) to scrap chapter pages.

### Config Local

- VM Options

```
-Dspring.profiles.active=local
```

- Enviroment - Using H2 Database

```
DRIVER_DB=org.h2.Driver;
LOGGING_LEVEL_ROOT=info;
PASSWORD_DB=;
URL_DB=jdbc:h2:mem:AZ\;DB_CLOSE_DELAY\=-1\;DB_CLOSE_ON_EXIT\=FALSE;
USER_DB=sa
```

---
Developed by [Jean Jacques Barros](https://github.com/jjeanjacques10)