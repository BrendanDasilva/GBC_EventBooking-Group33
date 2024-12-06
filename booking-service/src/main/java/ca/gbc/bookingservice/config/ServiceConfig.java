package ca.gbc.bookingservice.config;

import ca.gbc.roomservice.service.RoomService;
import ca.gbc.roomservice.service.RoomServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
    @Bean
    public RoomService roomService() {
        return new RoomServiceImpl();  // Return an instance of the concrete implementation
    }
}
