package ca.gbc.bookingservice.service;

import ca.gbc.bookingservice.dto.BookingServiceRequest;
import ca.gbc.bookingservice.model.BookingModel;
import ca.gbc.bookingservice.exception.RoomNotAvailableException;

import java.util.List;

public interface BookingService {

    // Method to create a new booking
    BookingModel createBooking(BookingServiceRequest bookingRequest) throws RoomNotAvailableException;

    // Method to get all bookings
    List<BookingModel> getAllBookings();

    // Method to update an existing booking
    BookingModel updateBooking(Long bookingId, BookingServiceRequest updatedRequest) throws RoomNotAvailableException;

    // Method to delete a booking
    void deleteBooking(Long bookingId) throws RoomNotAvailableException;
}
