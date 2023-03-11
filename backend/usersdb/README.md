# UsersDB

Database for the users microservice.

The SQL script creates the `itausers` database and the `users` table.

### Table

CREATE TABLE users (

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; id bigserial primary key,

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; email      varchar(255),

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; first_name varchar(255),

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; last_name  varchar(255),

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; password   varchar(255)

);

By default the users table has one user inserted:

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; email: nik.kovacevic.5@gmail.com

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; password: nikovpassword

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; first_name: Nik

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; last_name: Kovacevic