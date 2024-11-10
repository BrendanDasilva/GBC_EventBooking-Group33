print('START');

// Connect to the 'event-service' database
db = db.getSiblingDB('event-service');

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

print('END')