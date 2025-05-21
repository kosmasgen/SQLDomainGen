CREATE TABLE student (
    student_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE course (
    course_id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL
);

CREATE TABLE student_course (
    id SERIAL PRIMARY KEY,
    student_id INT NOT NULL,
    course_id INT NOT NULL,
    grade DECIMAL(5,2),
    FOREIGN KEY (student_id) REFERENCES student(student_id),
    FOREIGN KEY (course_id) REFERENCES course(course_id)
);

CREATE TABLE department (
    department_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    parent_dept_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (parent_dept_id) REFERENCES department(department_id)
);

CREATE TABLE event (
    event_id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    location VARCHAR(255),
    event_type VARCHAR(50) NOT NULL,
    visibility_type VARCHAR(50) NOT NULL,
    creator_id INT NOT NULL,
    is_recurring BOOLEAN DEFAULT FALSE,
    recur_pattern_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (creator_id) REFERENCES user(user_id),
    FOREIGN KEY (recur_pattern_id) REFERENCES recurring_pattern(pattern_id)
);

CREATE TABLE event_assignment (
    assignment_id SERIAL PRIMARY KEY,
    event_id INT NOT NULL,
    user_id INT,
    department_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (event_id) REFERENCES event(event_id),
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (department_id) REFERENCES department(department_id)
);

CREATE TABLE event_exception (
    exception_id SERIAL PRIMARY KEY,
    event_id INT NOT NULL,
    exception_date DATE NOT NULL,
    is_rescheduled BOOLEAN DEFAULT FALSE,
    new_start_time TIMESTAMP,
    new_end_time TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (event_id) REFERENCES event(event_id)
);

CREATE TABLE time_off_request (
    request_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    type VARCHAR(50) NOT NULL,
    status VARCHAR(50) NOT NULL,
    supervisor_id INT,
    reason TEXT,
    comments TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (supervisor_id) REFERENCES user(user_id)
);

CREATE TABLE holiday (
    holiday_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    date DATE NOT NULL,
    is_recurring BOOLEAN DEFAULT FALSE,
    recur_pattern_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (recur_pattern_id) REFERENCES recurring_pattern(pattern_id)
);

CREATE TABLE department_day_off (
    day_off_id SERIAL PRIMARY KEY,
    department_id INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    date DATE NOT NULL,
    is_recurring BOOLEAN DEFAULT FALSE,
    recur_pattern_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (department_id) REFERENCES department(department_id),
    FOREIGN KEY (recur_pattern_id) REFERENCES recurring_pattern(pattern_id)
);

CREATE TABLE absence (
    absence_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    reason TEXT,
    duration_hours DECIMAL(5,2),
    is_notification BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(user_id)
);

CREATE TABLE user (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE recurring_pattern (
    pattern_id SERIAL PRIMARY KEY,
    pattern_type VARCHAR(50) NOT NULL,
    frequency VARCHAR(50),
    days_of_week VARCHAR(50),
    day_of_month INT,
    month_of_year INT,
    end_date DATE,
    end_after_occur INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE event_resource_booking (
    booking_id SERIAL PRIMARY KEY,
    event_id INT NOT NULL,
    generic_resource_id INT NOT NULL,
    booked_from TIMESTAMP NOT NULL,
    booked_until TIMESTAMP NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_event FOREIGN KEY (event_id) REFERENCES event(event_id),
    CONSTRAINT fk_generic_resource FOREIGN KEY (generic_resource_id) REFERENCES generic_resource(generic_resource_id)
);


CREATE TABLE user_profile (
    profile_id SERIAL PRIMARY KEY,
    bio TEXT,
    phone VARCHAR(20),
    user_id INT UNIQUE,
    FOREIGN KEY (user_id) REFERENCES user(user_id)
);

CREATE TABLE audit_log (
    log_id UUID PRIMARY KEY,
    action VARCHAR(100) NOT NULL,
    user_id INT,
    details TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(user_id)
);