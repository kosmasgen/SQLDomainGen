CREATE TABLE professor (
    id SERIAL PRIMARY KEY,
    p_name VARCHAR(100)
);

CREATE TABLE course (
    id SERIAL PRIMARY KEY,
    title VARCHAR(100),
    professor_id INT REFERENCES professor(id)
);

CREATE TABLE student (
    id SERIAL PRIMARY KEY,
    s_name VARCHAR(100)
);

CREATE TABLE enrollment (
    id SERIAL PRIMARY KEY,
    student_id INT REFERENCES student(id),
    course_id INT REFERENCES course(id)
);
