-- SEM Snake database setup script.

-- Delete any old data
drop table if exists users,scores;

-- Create the table
create table users
(
  username varchar(20) not null primary key,
  password varchar(200) not null
);

create table scores
(
    username varchar(20) not null primary key,
    score int not null,
    foreign key (username) references users(username)
);