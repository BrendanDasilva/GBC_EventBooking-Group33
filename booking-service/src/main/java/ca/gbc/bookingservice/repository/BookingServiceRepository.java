package ca.gbc.bookingservice.repository;

import ca.gbc.bookingservice.model.BookingModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingServiceRepository extends MongoRepository<BookingModel, String> {

	Optional<BookingModel> findByRoomIdAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
			String roomId, LocalDateTime startTime, LocalDateTime endTime);

	List<BookingModel> findByRoomIdAndStartTimeBetween(
			String roomId, LocalDateTime startTime, LocalDateTime endTime);
}
