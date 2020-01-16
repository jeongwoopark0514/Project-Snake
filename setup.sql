-- SEM Snake database setup script.

-- Delete any old data
drop table if exists users;
drop table if exists sessions;

-- Create the table
create table users
(
  username varchar(20) not null primary key,
  password varchar(200) not null
);

create table sessions
(
  cookie varchar(36) not null primary key,
  username varchar(20) not null
);