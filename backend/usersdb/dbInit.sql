\c postgres;

CREATE DATABASE itausers;

\c itausers;

CREATE TABLE users (
    id bigserial primary key,
    email      varchar(255),
    first_name varchar(255),
    last_name  varchar(255),
    password   varchar(255)
);

INSERT INTO users (email, first_name, last_name, password) VALUES ('nik.kovacevic.5@gmail.com', 'Nik', 'Kovacevic', '$2a$10$0j8HZ9C1hnaZ1MQhkWJ69ug4fMMCMAKyIoB/rj2H1S0jIF8725wLi');