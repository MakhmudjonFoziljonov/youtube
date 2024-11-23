CREATE TABLE IF NOT EXISTS profile (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    surname VARCHAR(255),
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    status VARCHAR(50),
    role VARCHAR(50) NOT NULL,
    visible BOOLEAN DEFAULT TRUE,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    photo_id VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS category (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS tag (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


-- Insert admin profile
INSERT INTO profile (name, surname, email, password, status, role, visible, created_date)
VALUES ('Admin', 'User', 'admin@example.com', '$2y$10$37CuDkrC0amPwjNVWPRhp.OehsobyalLRWAbPw5R6VEhZIZb5aowO', 'ACTIVE', 'ROLE_ADMIN', TRUE, CURRENT_TIMESTAMP);

-- Insert moderator profile
INSERT INTO profile (name, surname, email, password, status, role, visible, created_date)
VALUES ('Moderator', 'User', 'moderator@example.com', '$2y$10$J87aLOHZQGR3BeTkU9.xQ.lGd433Ik3lVOgZaJIHgfvrx6kFv4gyq', 'ACTIVE', 'ROLE_MODERATOR', TRUE, CURRENT_TIMESTAMP);

-- Insert default categories
INSERT INTO category (name, created_date)
VALUES
    ('Technology', CURRENT_TIMESTAMP),
    ('Health', CURRENT_TIMESTAMP),
    ('Education', CURRENT_TIMESTAMP),
    ('Business', CURRENT_TIMESTAMP);

-- Insert default tags
INSERT INTO tag (name, created)
VALUES
    ('Java', CURRENT_TIMESTAMP),
    ('Spring Boot', CURRENT_TIMESTAMP),
    ('React', CURRENT_TIMESTAMP),
    ('Wellness', CURRENT_TIMESTAMP),
    ('Finance', CURRENT_TIMESTAMP);