CREATE TABLE rooms (
    id SERIAL PRIMARY KEY,
    room_name VARCHAR(100) NOT NULL,
    capacity INTEGER NOT NULL,
    features TEXT,
    available BOOLEAN NOT NULL DEFAULT TRUE
    );