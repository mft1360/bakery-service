# Bakery Service
The bakery has decided to start
selling their produce prepackaged in bunches and charging the customer on a per pack basis.
In this Project The bakery would be able to define a lot of sort of packages.
And It is fully dynamic.
In this project I preferred to use H2 DataBase for saving products and packs in data.sql file that you will be able to change 
them according your products.   
there is one API on swagger to test it. After running this project you can see them with this URL
http://localhost:8081/swagger-ui.html  

## the pre-requisites
* You have to install Java 8.
* You have to install Maven 3.

## Technologies: 
* Spring WEB
* Spring AOP
* Spring Data JPA
* H2
* Swagger
* Spring Test

### Run test methods:
```
Use "mvn clean package" to run the tests with HSQL DB.
```

### Run in development envirenment:
```
To run the project with **spring-boot:run** in development environment.
```

### Run in production environment:
```
Use java -jar file with the below command:  
**java -jar -Dspring.profiles.active=prod jarName.jar**
```

## Task lists
You can use these following stack technology to make the application more enterprise:
- [ ] Spring Security (I Could use spring security but I have to implement or create another instance to authenticate)
- [ ] Spring Cloud
- [ ] Spring Session
- [ ] Spring Oauth2
- [ ] API gateway like Zuul
- [ ] Circuit breaker
- [ ] Spring Cache
