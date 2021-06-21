## About the Assignment
 
* A user can add a new service with url, a name
* Added services are kept in H2 DB
* Added Time and Updated Time are also stored in the service Info
* A user can create/update/delete services
* When you reload the page you can see the latest updates.
* URL Validation was done during adding and updating the service with the help of common validator library (UrlValidator).
* Poll mechanism is triggered when the page is reloaded and trigger function makes a post call periodically. When backend poll logic is triggered, it would be scheduled to work in every 1 sec. Backend method checks the url health async responses of OkHttpClient.

## About the Technical Details
* The microservice runs with **Java 1.8** and it was developed with spring boot project.
* Google Chrome is used as a default browser.
* For object-relational mapping **Spring Data JPA** is used.
* **H2 in memory db** is used to persist the data. 
* In each **restart** database will **drop and create**.
* **Swagger** is used for **REST API** documentation  
* **JUnit and Mockito** framework is used in writing and running your unit tests.
* **SonarQube analyze** was done with SonarLint Eclipse plugin.
* Spring DevTools was used during development.

### GitHub  
  The application can be downloaded to local repository by the following command after configuring SSH settings : 
  
  ```
  git clone git@github.com:OzlemDoganGitPortfolio/assignment.git
  ```
### Run The Application
The following command from the project home directory could executed to run the application:
  
```
$ java -jar assignment-0.0.1-SNAPSHOT.jar  
```
```
$ ./mvnw spring-boot:run 
```
* Then, access the following url, from two different browser session windows/tabs:

```
http://localhost:8080
```
* If you want to check the database: (**username: sa**, **password:password**)

```
http://localhost:8080/h2-console/
```
* API documentation

```
http://localhost:8080/swagger-ui.html
```
### Database Configurations - Embedded H2 database
```
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.h2.console.settings.trace=false
spring.h2.console.settings.web-allow-others=false
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.hbm2ddl.auto=create-drop
```

