CREATE TABLE person (
    person_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    personal_number VARCHAR(50) NOT NULL UNIQUE,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(255),
    date_of_birth DATE,
    gender TINYINT,
    phone_number VARCHAR(50));
