--liqquibase formatted sql


--changeset team3:1
CREATE DATABASE logger_team3;

CREATE SCHEMA logger_team3;

CREATE TABLE IF NOT EXISTS students
(
    student_id   SERIAL PRIMARY KEY
);

--changeset team3:2
CREATE TABLE IF NOT EXISTS logs
(
    log_id   SERIAL UNIQUE PRIMARY KEY,
    student_id INT REFERENCES students (id) ON DELETE CASCADE,
    updateDateTime TIMESTAMP ,  --     UNIQUE NOT NULL,
    message VARCHAR(128) NOT NULL
);

--changeset team3:2
