-- create a new database and switch to it
create database xkcddata;
use xkcddata;

-- create Xkcd table
create table xkcd (number BIGINT not null, title VARCHAR(255), image_url VARCHAR(255), transcript TEXT, alt TEXT, primary key (number));