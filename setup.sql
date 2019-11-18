-- SEM Snake database setup script.

-- Delete any old data
drop table if exists users;

-- Create the table
create table users
(
  username varchar(20) not null primary key,
  password varchar(20) not null
);

-- Insert some dummy data
INSERT INTO users(username, password) VALUES ("Test", "TPW"), ("Bob", "BOB1234"),("three","3");