package ca.gbc.bookingservice.service;

import ca.gbc.bookingservice.dto.BookingServiceRequest;
import ca.gbc.bookingservice.model.BookingModel;
import ca.gbc.bookingservice.exception.RoomNotAvailableException;

import java.util.List;

public interface BookingService {

    /**
     * Creates a new booking.
     * @param bookingRequest the booking request containing the necessary details
     * @return the created booking model
     * @throws RoomNotAvailableException if the room is not available for the requested time range
     */
    BookingModel createBooking(BookingServiceRequest bookingRequest) throws RoomNotAvailableException;

    /**
     * Retrieves all bookings.
     * @return a list of all bookings
     */
    List<BookingModel> getAllBookings();

    /**
     * Updates an existing booking.
     * @param bookingId the ID of the booking to update
     * @param updatedRequest the updated booking request
     * @return the updated booking model
     * @throws RoomNotAvailableException if the room is not available for the new time range
     */
    BookingModel updateBooking(Long bookingId, BookingServiceRequest updatedRequest) throws RoomNotAvailableException;

    /**
     * Deletes an existing booking.
     * @param bookingId the ID of the booking to delete
     * @throws RoomNotAvailableException if the booking to delete is not found
     */
    void deleteBooking(Long bookingId) throws RoomNotAvailableException;
}
