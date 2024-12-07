-- Create database and grant privileges
-- CREATE DATABASE "user-service";
-- GRANT ALL PRIVILEGES ON DATABASE "user-service" TO "admin";
--
-- Create users table
CREATE TABLE IF NOT EXISTS users (
    id UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    role VARCHAR(20) NOT NULL,
    user_type VARCHAR(50)
    );

-- Insert dummy data into users table
INSERT INTO users (id, name, email, role, user_type) VALUES
('550e8400-e29b-41d4-a716-446655440000',
 'Jim Johnson', 'jim.j@yahoo.com', 'STUDENT', 'Full-time'),
('550e8400-e29b-41d4-a716-446655440001',
 'Paul Pastor', 'ppastor@gmail.com', 'STAFF', 'Part-time'),
('550e8400-e29b-41d4-a716-446655440002',
 'Grace Gibbs', 'gracegib@hotmail.com', 'FACULTY', 'Contract');

