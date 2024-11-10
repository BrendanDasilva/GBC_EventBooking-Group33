-- Create database and grant privileges
CREATE DATABASE "room-service";
GRANT ALL PRIVILEGES ON DATABASE "room-service" TO "admin";

-- Create rooms table
CREATE TABLE IF NOT EXISTS rooms (
    id SERIAL PRIMARY KEY,
    room_name VARCHAR(100) NOT NULL UNIQUE,
    capacity INTEGER NOT NULL,
    available BOOLEAN NOT NULL DEFAULT TRUE
    );

-- Create room_features table to store list of features
CREATE TABLE IF NOT EXISTS room_features (
    id SERIAL PRIMARY KEY,
    room_id INTEGER NOT NULL REFERENCES rooms(id) ON DELETE CASCADE,
    feature VARCHAR(255) NOT NULL
    );

-- Insert dummy data into rooms table
INSERT INTO rooms (room_name, capacity, available) VALUES
('CS Conference Room A', 10, true),
('Lecture Hall 202', 80, true),
('Study Room 101', 6, true);

-- Insert dummy data into room_features table
INSERT INTO room_features (room_id, feature) VALUES
(1, 'Projector'),
(1, 'Whiteboard'),
(1, 'Electrical Outlets'),
(2, 'Sound System'),
(2, 'Podium'),
(3, 'Noise Reduction')