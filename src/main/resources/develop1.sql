CREATE TABLE motorcyclist
(
    id BIGSERIAL PRIMARY KEY ,
    name VARCHAR(32) NOT NULL UNIQUE
);

CREATE TABLE bike
(
    id BIGSERIAL PRIMARY KEY ,
    name VARCHAR(64) NOT NULL,
    motorcyclist_id BIGINT REFERENCES motorcyclist(id) ON DELETE CASCADE NOT NULL
);

CREATE TABLE course
(
    id BIGSERIAL PRIMARY KEY ,
    name VARCHAR(64) NOT NULL UNIQUE
);

CREATE TABLE student
(
    id BIGSERIAL PRIMARY KEY ,
    name VARCHAR(32) NOT NULL UNIQUE ,
    course_id BIGINT REFERENCES course(id) ON DELETE CASCADE NOT NULL
);

CREATE TABLE student_profile
(
    student_id BIGINT REFERENCES student(id) ON DELETE CASCADE PRIMARY KEY ,
    gpa NUMERIC NOT NULL
);

CREATE TABLE trainer
(
    id BIGSERIAL PRIMARY KEY ,
    name VARCHAR(32) NOT NULL UNIQUE ,
    profession VARCHAR(32) NOT NULL
);

CREATE TABLE course_trainer
(
    id BIGSERIAL PRIMARY KEY,
    course_id BIGINT REFERENCES course(id) ON DELETE CASCADE NOT NULL ,
    trainer_id BIGINT REFERENCES trainer(id) ON DELETE CASCADE NOT NULL
);

DROP TABLE course;
DROP TABLE student;
DROP TABLE student_profile;
DROP TABLE profile;
DROP TABLE trainer;
DROP TABLE course_trainer;
