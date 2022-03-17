create table if not exists users (
    id serial primary key,
    name varchar(100) not null,
    email varchar(50) not null,
    password varchar(255) not null,
    role varchar(10) not null
);