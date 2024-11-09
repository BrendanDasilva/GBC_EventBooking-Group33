package ca.gbc.comp3095_gbc_event_booking;

import org.springframework.boot.SpringApplication;

public class TestComp3095GbcEventBookingApplication {

    public static void main(String[] args) {
        SpringApplication.from(Comp3095GbcEventBookingApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
