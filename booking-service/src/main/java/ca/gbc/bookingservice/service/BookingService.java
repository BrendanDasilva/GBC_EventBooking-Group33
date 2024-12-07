package ca.gbc.bookingservice.service;

import ca.gbc.bookingservice.dto.BookingServiceRequest;
import ca.gbc.bookingservice.model.BookingModel;
import ca.gbc.bookingservice.exception.RoomNotAvailableException;

import java.util.List;

public interface BookingService {

    BookingModel createBooking(BookingServiceRequest bookingRequest) throws RoomNotAvailableException;

    List<BookingModel> getAllBookings();

    BookingModel updateBooking(String bookingId, BookingServiceRequest updatedRequest) throws RoomNotAvailableException;

    void deleteBooking(String bookingId) throws RoomNotAvailableException;
}
