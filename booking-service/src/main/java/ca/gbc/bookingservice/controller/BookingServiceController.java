package ca.gbc.bookingservice.controller;

import ca.gbc.bookingservice.dto.BookingServiceRequest;
import ca.gbc.bookingservice.dto.BookingServiceResponse;
import ca.gbc.bookingservice.exception.RoomNotAvailableException;
import ca.gbc.bookingservice.model.BookingModel;
import ca.gbc.bookingservice.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bookings")
public class BookingServiceController {

    private final BookingService bookingService;

    @Autowired
    public BookingServiceController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // CREATE Booking
    @PostMapping
    public ResponseEntity<BookingServiceResponse> createBooking(@RequestBody BookingServiceRequest bookingRequest) {
            BookingModel bookingModel = bookingService.createBooking(bookingRequest);
            BookingServiceResponse response = new BookingServiceResponse();
            return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // READ all Bookings
    @GetMapping
    public ResponseEntity<List<BookingModel>> getAllBookings() {
        List<BookingModel> bookings = bookingService.getAllBookings();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    // UPDATE Booking
    @PutMapping("/{bookingId}")
    public ResponseEntity<BookingServiceResponse> updateBooking(@PathVariable Long bookingId, @RequestBody BookingServiceRequest updatedRequest) {
        try {
            BookingModel updatedBooking = bookingService.updateBooking(bookingId, updatedRequest);
            BookingServiceResponse response = new BookingServiceResponse(updatedBooking.getId(), "Booking updated successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RoomNotAvailableException e) {
            return new ResponseEntity<>(new BookingServiceResponse(null, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    // DELETE Booking
    @DeleteMapping("/{bookingId}")
    public ResponseEntity<BookingServiceResponse> deleteBooking(@PathVariable Long bookingId) {
        try {
            bookingService.deleteBooking(bookingId);
            BookingServiceResponse response;
            response = new BookingServiceResponse(bookingId, "Booking deleted successfully.");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } catch (RoomNotAvailableException e) {
            return new ResponseEntity<>(new BookingServiceResponse(null, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
