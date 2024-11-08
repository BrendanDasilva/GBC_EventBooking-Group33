package ca.gbc.eventservice.repository;

import ca.gbc.eventservice.model.EventServiceModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventServiceRepository extends MongoRepository<EventServiceModel, String> {

}


