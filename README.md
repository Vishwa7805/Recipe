DataBase Setup:
In application.property change the user_name and password.
Create the database using the given command: CREATE DATABASE recipe
Table need not be create.
(Optional) If you want manually to select the data use command: SELECT * FROM recipes

spring.datasource.url=jdbc:mysql://localhost:3306/database_name => change database name here
spring.datasource.username=root => user_name
spring.datasource.password=PASSWORD => your_password
spring.servlet.multipart.max-file-size=20MB => Change size based on input size
spring.servlet.multipart.max-request-size=20MB => Change size based on input size
server.port=8080

API Testing:
To run the spring application use the command: mvn spring-boot:run
Use Postman to parse the JSON data/file:Open the Postman and go to the POST request and use the link to upload the JSON file: "http://localhost:8080/recipes"
In body select the form-data and give:Key = file, Change the file type as Text → File, Then click the Send option.

Add a new Recipe:
In Postman change to POST and use: "http://localhost:8080/recipes/add-recipe", Select body and then choose raw then give the input as JSON.
Example:
{
"title": "Chocolate Cake",
"rating": 3.5,
"cuisine": "Dessert",
"prep_time": 20,
"cook_time": 40,
"description": "A rich chocolate cake...",
"nutrients": {
"calories": "500 kcal",
"carbohydrateContent": "60 g",
"proteinContent": "6 g",
"fatContent": "25 g"
},
"serves": "6 servings"
}

To get the top rating food with limit value use the API: http://localhost:8080/recipes/top ,Then in key = limit, value = "any number"
Or use: http://localhost:8080/recipes/top?limit=2
Use this link for top 2 according to the Rating.
