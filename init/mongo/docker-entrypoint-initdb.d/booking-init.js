print('START');

// Connect to the 'booking-service' database
db = db.getSiblingDB('booking-service');

// Create the 'bookings' collection and insert dummy data
db.bookings.insertMany([
    {
        _id: "booking_01",
        userId: "550e8400-e29b-41d4-a716-446655440001",  // Paul Pastor's UUID
        roomId: "1",  // Corresponds to CS Conference Room A
        startTime: ISODate("2024-12-01T09:00:00Z"),  // Example start time
        endTime: ISODate("2024-12-01T10:00:00Z"),    // Example end time
        purpose: "Make New Friends"
    },
    {
        _id: "booking_02",
        userId: "550e8400-e29b-41d4-a716-446655440002",  // Grace Gibbs's UUID
        roomId: "2",  // Corresponds to Lecture Hall 202
        startTime: ISODate("2024-12-01T11:00:00Z"),
        endTime: ISODate("2024-12-01T12:00:00Z"),
        purpose: "Heist Shopping"
    },
    {
        _id: "booking_03",
        userId: "550e8400-e29b-41d4-a716-446655440000",  // Jim Johnson's UUID
        roomId: "3",  // Corresponds to Study Room 101
        startTime: ISODate("2024-12-01T13:00:00Z"),
        endTime: ISODate("2024-12-01T14:00:00Z"),
        purpose: "Love is Blind Date"
    }
]);


print('END')