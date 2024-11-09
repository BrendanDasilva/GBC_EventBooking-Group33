package ca.gbc.eventservice;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

@Configuration
public class TestcontainersConfiguration {

	private static final MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName
			.parse("mongo:latest"));

	@BeforeAll
	public static void startMongoContainer(){
		mongoDBContainer.start();
		System.setProperty("MONGO_PORT", mongoDBContainer.getMappedPort(27017).toString());
	}

	@AfterAll
	public static void stopMongoContainer(){
		mongoDBContainer.stop();
	}

	@Bean
	public MongoTemplate mongoTemplate(){
		String mongoUrl = "mongo://admin:password@localhost:" + System.getProperty("MONGO_PORT") + "/event-service";
		return new MongoTemplate(new SimpleMongoClientDatabaseFactory(mongoUrl));
	}
}
