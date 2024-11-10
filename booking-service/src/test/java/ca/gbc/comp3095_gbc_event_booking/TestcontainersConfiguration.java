package ca.gbc.comp3095_gbc_event_booking;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration
public class TestcontainersConfiguration {

    // Start MongoDB container for testing
    @Bean
    public MongoDBContainer mongoDBContainer() {
        // Use the official MongoDB Docker image
        MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:latest"))
                .withExposedPorts(27017);  // Expose the default MongoDB port

        // Start the container before running tests
        mongoDBContainer.start();

        return mongoDBContainer;
    }
}
