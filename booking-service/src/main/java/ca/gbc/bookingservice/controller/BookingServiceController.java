package ca.gbc.bookingservice.controller;

import ca.gbc.bookingservice.dto.BookingServiceRequest;
import ca.gbc.bookingservice.dto.BookingServiceResponse;
import ca.gbc.bookingservice.exception.RoomNotAvailableException;
import ca.gbc.bookingservice.model.BookingModel;
import ca.gbc.bookingservice.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingServiceController {

	private final BookingService bookingService;

	// Constructor Injection for BookingService
	public BookingServiceController(BookingService bookingService) {
		this.bookingService = bookingService;
	}

	@PostMapping
	public ResponseEntity<BookingServiceResponse> createBooking(@RequestBody BookingServiceRequest bookingRequest) {
		try {
			// Attempt to create a booking using the BookingService
			BookingModel createdBooking = bookingService.createBooking(bookingRequest);

			// Build a response to indicate booking creation success
			BookingServiceResponse response = new BookingServiceResponse(
				createdBooking.getId(), // Booking ID from the created booking
				"Booking created successfully." // Success message
			);

			// Return ResponseEntity with HTTP Status CREATED (201)
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (RoomNotAvailableException e) {
			// Handle exception where the room is not available for the specified time
			BookingServiceResponse response = new BookingServiceResponse(
				null,  // No ID for error response
				e.getMessage()  // Error message from exception
			);

			// Return ResponseEntity with HTTP Status BAD REQUEST (400)
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping
	public ResponseEntity<List<BookingServiceResponse>> getAllBookings() {
		List<BookingModel> bookings = bookingService.getAllBookings();

		// Convert BookingModel list to BookingServiceResponse list
		List<BookingServiceResponse> responseList = bookings.stream()
			.map(BookingServiceResponse::fromBookingModel)
			.toList();

		// Return ResponseEntity with HTTP Status OK (200)
		return new ResponseEntity<>(responseList, HttpStatus.OK);
	}


	@PutMapping("/{id}")
	public ResponseEntity<BookingServiceResponse> updateBooking(@PathVariable Long id,
																@RequestBody BookingServiceRequest updatedRequest) {
		try {
			// Attempt to update the booking using the BookingService
			BookingModel updatedBooking = bookingService.updateBooking(id, updatedRequest);

			// Build a response for the updated booking
			BookingServiceResponse response = new BookingServiceResponse(
				updatedBooking.getId(), // Updated Booking ID
				"Booking updated successfully." // Success message
			);

			// Return ResponseEntity with HTTP Status OK (200)
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (RoomNotAvailableException e) {
			// Handle exception if the room is not available during update
			BookingServiceResponse response = new BookingServiceResponse(
				null, // No ID for error response
				e.getMessage() // Error message from exception
			);

			// Return ResponseEntity with HTTP Status BAD REQUEST (400)
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}


	@DeleteMapping("/{id}")
	public ResponseEntity<BookingServiceResponse> deleteBooking(@PathVariable Long id) {
		try {
			// Attempt to delete the booking with the specified ID
			bookingService.deleteBooking(id);

			// Build a response for successful deletion
			BookingServiceResponse response = new BookingServiceResponse(
				null,  // No ID for successful deletion response
				"Booking deleted successfully." // Success message
			);

			// Return ResponseEntity with HTTP Status OK (200)
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			// Handle error (e.g., booking not found)
			BookingServiceResponse response = new BookingServiceResponse(
				null,  // No ID for error response
				"Booking not found"  // Error message
			);

			// Return ResponseEntity with HTTP Status NOT FOUND (404)
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}
}