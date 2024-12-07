-- Create rooms table
CREATE TABLE rooms (
    id SERIAL PRIMARY KEY,
    room_name VARCHAR(100) NOT NULL UNIQUE,
    capacity INTEGER NOT NULL,
    available BOOLEAN NOT NULL DEFAULT TRUE
    );