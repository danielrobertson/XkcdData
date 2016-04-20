-- create a new database and switch to it
create database xkcddata;
use xkcddata;

-- create Xkcd table
create table xkcd (number BIGINT, title VARCHAR(255), image_url VARCHAR(255), transcript TEXT, alt TEXT);