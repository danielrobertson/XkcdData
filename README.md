# XkcdData
A RESTful wrapper for Xkcd comics that pulls data from [Xkcd](http://xkcd.com) and exposes it as a REST API.

This project is in development.

## Usage
Search for a relevant Xkcd comic
http://104.236.3.239:8080/xkcddata/api/relevant/query/{query-text}

Load Xkcd comics in range N to M inclusive
http://104.236.3.239:8080/xkcddata/api/load/begin/{N}/end/{M}

## Developer Environment Setup

### Prerequisites
- Java 8 
- Gradle 
- MySQL
- Update CATALINA_HOME in deploy.sh

### MySQL
- Set environment variables DB_PATH, DB_USER, and DB_PASSWORD
- Create a new database and switch to it  
  ```create database xkcddata;
  use xkcddata;```
- Create a new schema   
`create table xkcd (number BIGINT not null, title VARCHAR(255), image_url VARCHAR(255), transcript TEXT, alt TEXT, primary key (number));`

## Build and Run
Build  
`gradle build`  
`gradle war`  

Deploy  
`sudo gradle deployTomcat`

