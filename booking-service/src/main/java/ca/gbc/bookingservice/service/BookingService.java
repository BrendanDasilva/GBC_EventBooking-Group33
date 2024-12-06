package ca.gbc.bookingservice.service;

import ca.gbc.bookingservice.dto.BookingServiceRequest;
import ca.gbc.bookingservice.model.BookingModel;
import ca.gbc.bookingservice.exception.RoomNotAvailableException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface BookingService {

    @CircuitBreaker(name = "bookingServiceCircuitBreaker", fallbackMethod = "fallbackMethod")
    BookingModel createBooking(BookingServiceRequest bookingRequest) throws RoomNotAvailableException;

    List<BookingModel> getAllBookings();

    BookingModel updateBooking(Long bookingId, BookingServiceRequest updatedRequest) throws RoomNotAvailableException;

    void deleteBooking(Long bookingId) throws RoomNotAvailableException;
}
