package ca.gbc.bookingservice.dto;

import ca.gbc.bookingservice.model.BookingModel;
import ca.gbc.bookingservice.repository.BookingServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceRequest {

	private final BookingServiceRepository bookingRepository;

	// Constructor injection: The BookingRepository is passed as a constructor argument
	public BookingServiceRequest(BookingServiceRepository bookingRepository) {
		this.bookingRepository = bookingRepository;
	}

	public BookingModel createBooking(BookingModel booking) throws Exception {
		// Check for overlapping bookings (prevent double-booking)
		Optional<BookingModel> existingBooking = bookingRepository.findByRoomIdAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
				booking.getRoomId(), booking.getStartTime(), booking.getEndTime());

		if (existingBooking.isPresent()) {
			throw new Exception("The room is already booked for the specified time range.");
		}

		// Save and return the newly created booking
		return bookingRepository.save(booking);
	}

	// READ: Get all bookings
	public List<BookingModel> getAllBookings() {
		return bookingRepository.findAll();
	}
	// UPDATE: Update an existing booking
	public BookingModel updateBooking(String id, BookingModel updatedBooking) throws Exception {
		Optional<BookingModel> existingBooking = bookingRepository.findById(id);

		if (!existingBooking.isPresent()) {
			throw new Exception("Booking not found.");
		}

		// If the room is already booked within the new time range, throw an exception
		Optional<BookingModel> overlappingBooking = bookingRepository.findByRoomIdAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
				updatedBooking.getRoomId(), updatedBooking.getStartTime(), updatedBooking.getEndTime());

		if (overlappingBooking.isPresent() && !overlappingBooking.get().getId().equals(id)) {
			throw new Exception("The room is already booked for the specified time range.");
		}

		// Update the booking and save it
		BookingModel existing = existingBooking.get();
		existing.setUserId(updatedBooking.getUserId());
		existing.setRoomId(updatedBooking.getRoomId());
		existing.setStartTime(updatedBooking.getStartTime());
		existing.setEndTime(updatedBooking.getEndTime());
		existing.setPurpose(updatedBooking.getPurpose());

		return bookingRepository.save(existing);
	}

	// DELETE: Delete a booking by its ID
	public void deleteBooking(String id) throws Exception {
		Optional<BookingModel> existingBooking = bookingRepository.findById(id);

		if (!existingBooking.isPresent()) {
			throw new Exception("Booking not found.");
		}

		// Delete the booking
		bookingRepository.deleteById(id);
	}

}
