print('START');

// Connect to the 'event-service' database
db = db.getSiblingDB('event-service');
db.createCollection("events");
// Create the 'events' collection and insert dummy data
db.events.insertMany([
    {
        // Jim Johnson
        _id: "event_01",
        eventName: "Annual Date",
        organizerId: "550e8400-e29b-41d4-a716-446655440000",
        eventType: "Study Date",
        expectedAttendees: 2
    },
    {
        // Paul Pastor
        _id: "event_02",
        eventName: "Tech TedTalk",
        organizerId: "550e8400-e29b-41d4-a716-446655440001",
        eventType: "Networking",
        expectedAttendees: 12
    },
    {
        // Grace Gibbs
        _id: "event_03",
        eventName: "Workshop for Shopping",
        organizerId: "550e8400-e29b-41d4-a716-446655440002",
        eventType: "Workshop",
        expectedAttendees: 30
    }
]);

// Connect to the 'booking-service' database
db = db.getSiblingDB('booking-service');
db.createCollection("bookings");
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

db = db.getSiblingDB("approval-service");
db.createCollection("approvals");

print('END');