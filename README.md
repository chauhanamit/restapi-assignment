# restapi-assignment

Pre-requisites:-

	 JDK 11
	 Maven 4.0.0 and above

Technologies used:-

    Spring Boot 2.4.1
    Spring Security
    HTTP Basic Authentication
    Spring Data JPA
    Lombok
    Swagger2
    JUnit 4
    H2 database(In memory database)
    
Build:-

	step 1)Clone the git repo to your local using terminal and run command  "git clone https://github.com/chauhanamit/restful-api-assignment.git"
	
	step 2)Go to root folder of the cloned repo using open terminal and run command "mvn clean install"
	
	After executing above commands code will compile ,test and "rest-api.jar" get generate at target folder of project.

Deploy / Running code:-

	1)Once build get successfully built then follow belows to run application.
	2)Start docker desktop at local machine
	3)Run below command one by one
	   >> docker build . -t rest-api-assignment
	   >> docker run -p 8080:8080 -d 
	4) Now Application will up and running. Now test below Rest API's using Swaggger.   


Test Rest Api's using Swagger:-

	Swagger url :- http://localhost:8080/api/v1/swagger-ui.html


REST Services(Authorization required):-

  Authentication API :-
  
    POST http://localhost:8080/api/v1/authenticate
    
  Person Details API's :- 
  
	 GET http://localhost:8080/api/v1/person
	 GET http://localhost:8080/api/v1/person/{id}
	 POST http://localhost:8080/api/v1/person
	 PUT http://localhost:8080/api/v1/person/{id}
	 DELETE http://localhost:8080/api/v1/person/{id}
	
Below list of credentials for Authentication API.

	USER_NAME   PASSWORD
	user1       password
	user2       password
	user3       password	
	
Note:-Authentication Api used to authenticate user using credentials.Once user get authorized,then application issue JWT token ,that token need to use for downstream api's to access resources.(Person Details API's)	
