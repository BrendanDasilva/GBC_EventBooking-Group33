package ca.gbc.bookingservice.service;

import ca.gbc.bookingservice.dto.BookingServiceRequest;
import ca.gbc.bookingservice.dto.BookingServiceResponse;

import java.util.List;

public interface BookingService {

    BookingServiceResponse createBooking(BookingServiceRequest request);

    List<BookingServiceResponse> getAllBookings();

    BookingServiceResponse getBookingById(String id);

    BookingServiceResponse updateBooking(String id, BookingServiceRequest request);

    void deleteBooking(String id);
}