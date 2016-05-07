# XkcdData
A RESTful wrapper for Xkcd comics that pulls data from [Xkcd](http://xkcd.com) and exposes it as a REST API.

This project is in development.

## Usage
Search for a relevant Xkcd comic for "earth"
http://104.236.3.239:8080/xkcddata/api/relevant/query/earth

Load Xkcd comics in range 1670 to 1675 inclusive
http://104.236.3.239:8080/xkcddata/api/load/begin/1670/end/1675

## Developer Environment
### Prerequisites
- Java 8
- Gradle
- MySQL
- Tomcat 8
- Update CATALINA_HOME in deploy.sh

### Database
- Set environment variables DB_PATH, DB_USER, and DB_PASSWORD
- Create a new database and switch to it
  ```create database xkcddata;
  use xkcddata;```
- Create a new schema
`create table xkcd (number BIGINT not null, title VARCHAR(255), image_url VARCHAR(255), transcript TEXT, alt TEXT, primary key (number));`

### Build and Run
- Build
`gradle build`

- Deploy
`gradle deployTomcat`

- Log file is $CATALINA_HOME/conf/catalina.out

