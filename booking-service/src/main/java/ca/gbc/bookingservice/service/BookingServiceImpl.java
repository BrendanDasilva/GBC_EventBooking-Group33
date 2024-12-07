package ca.gbc.bookingservice.service;

import ca.gbc.bookingservice.dto.BookingServiceRequest;
import ca.gbc.bookingservice.model.BookingModel;
import ca.gbc.bookingservice.exception.RoomNotAvailableException;
import ca.gbc.bookingservice.repository.BookingServiceRepository;
import ca.gbc.roomservice.service.RoomService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    private final RoomService roomService;  // Autowire RoomService (interface)
    private final BookingServiceRepository bookingRepository;

    @Autowired
    public BookingServiceImpl(RoomService roomService, BookingServiceRepository bookingRepository) {
        this.roomService = roomService;
        this.bookingRepository = bookingRepository;
    }

    @Override
    @CircuitBreaker(name = "bookingServiceCircuitBreaker", fallbackMethod = "fallbackMethod")
    public BookingModel createBooking(BookingServiceRequest bookingRequest) throws RoomNotAvailableException {
        // Step 1: Check room availability using RoomService
        boolean isRoomAvailable = roomService.checkRoomAvailability("Room" + bookingRequest.roomId());

        if (!isRoomAvailable) {
            throw new RoomNotAvailableException("The room is not available for the selected dates.");
        }

        // Step 2: Create the BookingModel object and use setters to set values
        BookingModel booking = new BookingModel();
        booking.setUserId(bookingRequest.userId());
        booking.setRoomId(bookingRequest.roomId());
        booking.setStartTime(bookingRequest.startTime());
        booking.setEndTime(bookingRequest.endTime());
        booking.setPurpose(bookingRequest.purpose());
        booking.setEmail(bookingRequest.userDetails().email());
        booking.setFirstName(bookingRequest.userDetails().firstName());
        booking.setLastName(bookingRequest.userDetails().lastName());

        // Step 3: Save the booking to the repository
        return bookingRepository.save(booking);
    }


    @Override
    public List<BookingModel> getAllBookings() {
        // Retrieve all bookings from the repository
        return bookingRepository.findAll();
    }

    @Override
    public BookingModel updateBooking(Long bookingId, BookingServiceRequest updatedRequest) throws RoomNotAvailableException {
        // Step 1: Check if the booking exists
        Optional<BookingModel> existingBooking = bookingRepository.findById(String.valueOf(bookingId));

        if (!existingBooking.isPresent()) {
            throw new RoomNotAvailableException("Booking not found.");
        }

        // Step 2: Check room availability for the updated booking
        boolean isRoomAvailable = roomService.checkRoomAvailability("Room" + updatedRequest.roomId());

        if (!isRoomAvailable) {
            throw new RoomNotAvailableException("The room is not available for the selected dates.");
        }

        // Step 3: Update the existing booking with the new data
        BookingModel existingBookingModel = existingBooking.get();
        existingBookingModel.setUserId(updatedRequest.userId());
        existingBookingModel.setRoomId(updatedRequest.roomId());
        existingBookingModel.setStartTime(updatedRequest.startTime());
        existingBookingModel.setEndTime(updatedRequest.endTime());
        existingBookingModel.setPurpose(updatedRequest.purpose());
        existingBookingModel.setEmail(updatedRequest.userDetails().email());
        existingBookingModel.setFirstName(updatedRequest.userDetails().firstName());
        existingBookingModel.setLastName(updatedRequest.userDetails().lastName());

        // Step 4: Save the updated booking
        return bookingRepository.save(existingBookingModel);
    }

    @Override
    public void deleteBooking(Long bookingId) throws RoomNotAvailableException {
        // Step 1: Check if the booking exists
        Optional<BookingModel> existingBooking = bookingRepository.findById(String.valueOf(bookingId));

        if (!existingBooking.isPresent()) {
            throw new RoomNotAvailableException("Booking not found.");
        }

        // Step 2: Delete the booking
        bookingRepository.deleteById(String.valueOf(bookingId));
    }

    // Fallback method for when the circuit breaker is triggered
    public BookingModel fallbackMethod(BookingServiceRequest bookingRequest, Throwable throwable) {
        // Return a default response, e.g., a new BookingModel or some fallback response
        return new BookingModel();
    }
}