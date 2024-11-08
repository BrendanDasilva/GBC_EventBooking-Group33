package ca.gbc.bookingservice.repository;

import ca.gbc.bookingservice.model.BookingModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BookingServiceRepository extends MongoRepository<BookingModel, String> {

	// Custom query to check if a room is already booked within a time range
	Optional<BookingModel> findByRoomIdAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
			String roomId, LocalDateTime startTime, LocalDateTime endTime);

	List<BookingModel> findByRoomIdAndStartTimeBetween(String roomId, LocalDateTime startTime, LocalDateTime endTime);
}
