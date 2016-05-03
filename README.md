# XkcdData
A RESTful wrapper for Xkcd comics that pulls data from [Xkcd](http://xkcd.com) and exposes it as a REST API.

This project is in development. 

## Developer Environment Setup 

### Prerequisites
- Java 8 
- Gradle 
- MySQL

### MySQL
- Set environment variables DB_PATH, DB_USER, and DB_PASSWORD
- Create a new database and switch to it  
  ```create database xkcddata;
  use xkcddata;```
- Create a new schema   
`create table xkcd (number BIGINT not null, title VARCHAR(255), image_url VARCHAR(255), transcript TEXT, alt TEXT, primary key (number));`

### Build and Run 
- `gradle tasks` to view a list of available tasks. 
- `gradle build` to build the project 
- `gradle loadXkcdData -PRange="['40', '50']"` to load Xkcd comics numbers 40 through 50 inclusive into the database 
