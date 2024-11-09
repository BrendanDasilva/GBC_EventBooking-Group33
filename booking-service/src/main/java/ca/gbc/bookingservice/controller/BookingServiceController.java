package ca.gbc.bookingservice.controller;

import ca.gbc.bookingservice.dto.BookingServiceRequest;
import ca.gbc.bookingservice.dto.BookingServiceResponse;
import ca.gbc.bookingservice.model.BookingModel;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
@SpringBootApplication
public class BookingServiceController {

    private final BookingServiceRequest bookingServiceRequest;

    // POST - Create a new booking
    @PostMapping
    public ResponseEntity<BookingServiceResponse> createBooking(@RequestBody BookingModel booking) {
        try {
            // Call service to create booking
            BookingModel newBooking = bookingServiceRequest.createBooking(booking);

            // Convert the model to response DTO
            BookingServiceResponse response = new BookingServiceResponse(
                    newBooking.getId(),
                    newBooking.getUserId(),
                    newBooking.getRoomId(),
                    newBooking.getStartTime(),
                    newBooking.getEndTime(),
                    newBooking.getPurpose()
            );

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // GET - Get all bookings
    @GetMapping
    public ResponseEntity<List<BookingServiceResponse>> getAllBookings() {
        // Get all bookings from the service
        List<BookingModel> bookings = bookingServiceRequest.getAllBookings();

        // Convert the list of models to a list of response DTOs
        List<BookingServiceResponse> responseList = bookings.stream()
                .map(booking -> new BookingServiceResponse(
                        booking.getId(),
                        booking.getUserId(),
                        booking.getRoomId(),
                        booking.getStartTime(),
                        booking.getEndTime(),
                        booking.getPurpose()
                ))
                .collect(Collectors.toList());

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    // PUT - Update an existing booking by ID
    @PutMapping("/{id}")
    public ResponseEntity<BookingServiceResponse> updateBooking(@PathVariable String id, @RequestBody BookingModel updatedBooking) {
        try {
            // Call the service to update booking
            BookingModel updated = bookingServiceRequest.updateBooking(id, updatedBooking);

            // Convert the updated model to response DTO
            BookingServiceResponse response = new BookingServiceResponse(
                    updated.getId(),
                    updated.getUserId(),
                    updated.getRoomId(),
                    updated.getStartTime(),
                    updated.getEndTime(),
                    updated.getPurpose()
            );

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // DELETE - Delete a booking by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable String id) {
        try {
            // Call the service to delete the booking
            bookingServiceRequest.deleteBooking(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
