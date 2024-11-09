package ca.gbc.bookingservice.repository;

import ca.gbc.bookingservice.model.BookingServiceModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingServiceRepository extends MongoRepository<BookingServiceModel, String> {

}