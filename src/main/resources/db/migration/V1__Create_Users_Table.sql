CREATE TABLE IF NOT EXISTS 	users (

    id SERIAL NOT NULL PRIMARY KEY,
    username varchar(50) UNIQUE,
    password varchar(50)

);