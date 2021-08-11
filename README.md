The Front end of the application is in production, therefore the webapis can be accessed using API development tools such as Postman or Insomia.

RUN . To run the application, run the individual services in Quarkus dev mode. . The databse service will go live on port: localhost:8081 and the organization service on port: localhost:8080. . Use an API development tool (Postman or Insomnia) to run the REST Api methods (GET and POST) on various endpoints as listed below.

    GET method on http://localhost:8081/jdbc/employees to get the list of employees directly from the database (db application).
    GET method on http://localhost:8081/jdbc/organizations to get the list of organizations directly from the database (db application).
    GET method on http://localhost:8080/employees to get the list of employees from the oragnization service (business application).
    GET method on http://localhost:8080/organizations to get the list of organizations from the oragnization service (business application).
    POST method on http://localhost:8080/employees to add en employee record using a JSON object as such: { "id": 8, "organizationId": 2, "name": "anyname", "age": anyage, "position": "anyposition" } - the "id" must be greater than 7 and then sequentially increase the id number on each post as I have not implemented the auto increment function and keep the organization id as either 1 or 2, again some lazyness to add some error handling on my part.

. You can also access the GET methods on any browser.

. This is my first README file, sorry for the bad explanations.

