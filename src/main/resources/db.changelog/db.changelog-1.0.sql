--liqquibase formatted sql


--changeset team3:1
CREATE TABLE IF NOT EXISTS students
(
    student_id   SERIAL PRIMARY KEY
);

--changeset team3:2
CREATE TABLE IF NOT EXISTS logs
(
    log_id   SERIAL UNIQUE PRIMARY KEY,
    student_id INT REFERENCES students (student_id) ON DELETE CASCADE,
    updateDateTime TIMESTAMP ,
    message VARCHAR(128) NOT NULL
);
