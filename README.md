# MangaJJ - Controling my collection

Project created to control my magazines collection. 

## Steps

- [x] Integrate with some API
  - Lazy load process
- [ ] Create a job to update my database
- [ ] Add authentication
- [ ] Deploy

## Technology

- [Java Spring Boot](https://spring.io/projects/spring-boot)
- [Hibernate](https://hibernate.org/)
- [MySQL](https://www.mysql.com/)
- [Docker](https://www.docker.com/)

### Config Database
Access the file **[application.properties](src/main/resources/application.yml)**

```
spring.datasource.username={username}
spring.datasource.password={password}
```

Using external API to populate database: https://github.com/jikan-me/jikan

---
Developed by [Jean Jacques Barros](https://github.com/jjeanjacques10)