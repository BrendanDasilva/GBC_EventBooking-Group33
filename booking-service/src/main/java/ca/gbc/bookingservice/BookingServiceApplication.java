package ca.gbc.bookingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ca.gbc")
public class BookingServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(BookingServiceApplication.class, args);
	}
}
