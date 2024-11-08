package ca.gbc.bookingservice.service;

import ca.gbc.bookingservice.model.BookingModel;

public interface BookingService {
    BookingModel createBooking(BookingModel booking);
}
