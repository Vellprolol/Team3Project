--liqquibase formatted sql

--changeset team3:1
CREATE TABLE IF NOT EXISTS roles
(
    id   SERIAL UNIQUE PRIMARY KEY,
    role VARCHAR(20)
);

--changeset team3:2
CREATE TABLE IF NOT EXISTS users
(
    id   SERIAL UNIQUE PRIMARY KEY,
    username VARCHAR(128),
    password VARCHAR(128),
    message VARCHAR(128) NOT NULL
);

--changeset team3:3
CREATE TABLE IF NOT EXISTS users_roles
(
    role_id INT REFERENCES roles (id) ON DELETE CASCADE,
    user_id INT REFERENCES users (id)
);

