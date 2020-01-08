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
    score_id int not null auto_increment primary key,
    username varchar(20) not null,
    score int not null,
    nickname varchar(20) not null,
    foreign key (username) references users(username)
);