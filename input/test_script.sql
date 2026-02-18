CREATE TABLE school (
                        id BIGSERIAL PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        address VARCHAR(255),
                        city VARCHAR(100),
                        zipcode VARCHAR(20),
                        created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE teacher (
                         id BIGSERIAL PRIMARY KEY,
                         first_name VARCHAR(100) NOT NULL,
                         last_name VARCHAR(100) NOT NULL,
                         email VARCHAR(255) NOT NULL,
                         enrollment_date DATE,
                         school_id BIGINT NOT NULL,
                         CONSTRAINT fk_teacher_school
                             FOREIGN KEY (school_id) REFERENCES school(id)
);


CREATE TABLE student (
                         id BIGSERIAL PRIMARY KEY,
                         first_name VARCHAR(100) NOT NULL,
                         last_name VARCHAR(100) NOT NULL,
                         email VARCHAR(255) NOT NULL,
                         enrollment_date DATE,
                         school_id BIGINT NOT NULL,
                         teacher_id BIGINT,
                         CONSTRAINT fk_student_school
                             FOREIGN KEY (school_id) REFERENCES school(id),
                         CONSTRAINT fk_student_teacher
                             FOREIGN KEY (teacher_id) REFERENCES teacher(id)
);

CREATE TABLE course (
                        id BIGSERIAL PRIMARY KEY,
                        code VARCHAR(50),
                        title VARCHAR(255) NOT NULL,
                        description TEXT,
                        credits INT,
                        school_id BIGINT NOT NULL,
                        teacher_id BIGINT,
                        CONSTRAINT fk_course_school
                            FOREIGN KEY (school_id) REFERENCES school(id),
                        CONSTRAINT fk_course_teacher
                            FOREIGN KEY (teacher_id) REFERENCES teacher(id)
);



CREATE TABLE course_student (
                                id BIGSERIAL PRIMARY KEY,
                                course_id BIGINT NOT NULL,
                                student_id BIGINT NOT NULL,
                                CONSTRAINT fk_course_student_course
                                    FOREIGN KEY (course_id) REFERENCES course(id) ON DELETE CASCADE,
                                CONSTRAINT fk_course_student_student
                                    FOREIGN KEY (student_id) REFERENCES student(id) ON DELETE CASCADE,
                                CONSTRAINT uq_course_student UNIQUE (course_id, student_id)
);

