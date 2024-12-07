package ca.gbc.bookingservice.controller;

import ca.gbc.bookingservice.dto.BookingServiceRequest;
import ca.gbc.bookingservice.dto.BookingServiceResponse;
import ca.gbc.bookingservice.model.BookingModel;
import ca.gbc.bookingservice.service.BookingService;
import ca.gbc.bookingservice.exception.RoomNotAvailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bookings")
public class BookingServiceController {

	private BookingService bookingService;

	@Autowired
	public void BookingController(BookingService bookingService) {
		this.bookingService = bookingService;
	}

	@PostMapping
	public ResponseEntity<BookingServiceResponse> createBooking(@RequestBody BookingServiceRequest bookingRequest) {
		try {
			BookingModel booking = bookingService.createBooking(bookingRequest);
			BookingServiceResponse response = BookingServiceResponse.fromBookingModel(booking);
			return ResponseEntity.ok(response);
		} catch (RoomNotAvailableException ex) {
			return ResponseEntity.badRequest().body(new BookingServiceResponse(null, null, null, null, null, null, ex.getMessage()));
		}
	}

	@GetMapping
	public ResponseEntity<List<BookingServiceResponse>> getAllBookings() {
		List<BookingServiceResponse> responses = bookingService.getAllBookings().stream()
				.map(BookingServiceResponse::fromBookingModel)
				.collect(Collectors.toList());
		return ResponseEntity.ok(responses);
	}

	@PutMapping("/{id}")
	public ResponseEntity<BookingServiceResponse> updateBooking(
			@PathVariable String id, @RequestBody BookingServiceRequest updatedRequest) {
		try {
			BookingModel updatedBooking = bookingService.updateBooking(id, updatedRequest);
			return ResponseEntity.ok(BookingServiceResponse.fromBookingModel(updatedBooking));
		} catch (RoomNotAvailableException ex) {
			return ResponseEntity.badRequest().body(new BookingServiceResponse(null, null, null, null, null, null, ex.getMessage()));
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteBooking(@PathVariable String id) {
		try {
			bookingService.deleteBooking(id);
			return ResponseEntity.ok("Booking deleted successfully.");
		} catch (RoomNotAvailableException ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}
}
