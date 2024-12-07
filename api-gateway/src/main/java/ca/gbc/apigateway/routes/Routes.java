package ca.gbc.apigateway.routes;

import org.springframework.http.HttpStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.server.mvc.filter.CircuitBreakerFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;

import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.setPath;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;

@Configuration
@Slf4j
public class Routes {

    // URLs for the new services
    @Value("${services.booking-url}")
    private String bookingServiceUrl;

    @Value("${services.room-url}")
    private String roomServiceUrl;

    @Value("${services.event-url}")
    private String eventServiceUrl;

    @Value("${services.user-url}")
    private String userServiceUrl;

    @Value("${services.approval-url}")
    private String approvalServiceUrl;

    @Bean
    public RouterFunction<ServerResponse> bookingServiceRoute() {
        log.info("Initializing booking-service route with URL: {}", bookingServiceUrl);

        return route("booking_service")
                .route(RequestPredicates.path("/api/booking"), request -> {
                    log.info("Received request for booking-service: {}", request.uri());
                    return HandlerFunctions.http(bookingServiceUrl).handle(request);
                })
                .filter(CircuitBreakerFilterFunctions
                        .circuitBreaker("bookingServiceCircuitBreaker", URI.create("forward:/fallbackRoute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> roomServiceRoute() {
        log.info("Initializing room-service route with URL: {}", roomServiceUrl);

        return route("room_service")
                .route(RequestPredicates.path("/api/room"), request -> {
                    log.info("Received request for room-service: {}", request.uri());
                    return HandlerFunctions.http(roomServiceUrl).handle(request);
                })
                .filter(CircuitBreakerFilterFunctions
                        .circuitBreaker("roomServiceCircuitBreaker", URI.create("forward:/fallbackRoute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> eventServiceRoute() {
        log.info("Initializing event-service route with URL: {}", eventServiceUrl);

        return route("event_service")
                .route(RequestPredicates.path("/api/event"), request -> {
                    log.info("Received request for event-service: {}", request.uri());
                    return HandlerFunctions.http(eventServiceUrl).handle(request);
                })
                .filter(CircuitBreakerFilterFunctions
                        .circuitBreaker("eventServiceCircuitBreaker", URI.create("forward:/fallbackRoute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> userServiceRoute() {
        log.info("Initializing user-service route with URL: {}", userServiceUrl);

        return route("user_service")
                .route(RequestPredicates.path("/api/user"), request -> {
                    log.info("Received request for user-service: {}", request.uri());
                    return HandlerFunctions.http(userServiceUrl).handle(request);
                })
                .filter(CircuitBreakerFilterFunctions
                        .circuitBreaker("userServiceCircuitBreaker", URI.create("forward:/fallbackRoute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> approvalServiceRoute() {
        log.info("Initializing approval-service route with URL: {}", approvalServiceUrl);

        return route("approval_service")
                .route(RequestPredicates.path("/api/approval"), request -> {
                    log.info("Received request for approval-service: {}", request.uri());
                    return HandlerFunctions.http(approvalServiceUrl).handle(request);
                })
                .filter(CircuitBreakerFilterFunctions
                        .circuitBreaker("approvalServiceCircuitBreaker", URI.create("forward:/fallbackRoute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> eventServiceSwaggerRoute() {
        return route("event_service_swagger")
                .route(RequestPredicates.path("/aggregate/event-service/v3/api-docs"),
                        HandlerFunctions.http(eventServiceUrl))
                .filter(CircuitBreakerFilterFunctions
                        .circuitBreaker("eventServiceSwaggerCircuitBreaker", URI.create("forward:/fallbackRoute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> bookingServiceSwaggerRoute() {
        return route("booking_service_swagger")
                .route(RequestPredicates.path("/aggregate/booking-service/v3/api-docs"),
                        HandlerFunctions.http(bookingServiceUrl))
                .filter(CircuitBreakerFilterFunctions
                        .circuitBreaker("bookingServiceSwaggerCircuitBreaker", URI.create("forward:/fallbackRoute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> roomServiceSwaggerRoute() {
        return route("room_service_swagger")
                .route(RequestPredicates.path("/aggregate/room-service/v3/api-docs"),
                        HandlerFunctions.http(roomServiceUrl))
                .filter(CircuitBreakerFilterFunctions
                        .circuitBreaker("roomServiceSwaggerCircuitBreaker", URI.create("forward:/fallbackRoute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> userServiceSwaggerRoute() {
        return route("user_service_swagger")
                .route(RequestPredicates.path("/aggregate/user-service/v3/api-docs"),
                        HandlerFunctions.http(userServiceUrl))
                .filter(CircuitBreakerFilterFunctions
                        .circuitBreaker("userServiceSwaggerCircuitBreaker", URI.create("forward:/fallbackRoute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> approvalServiceSwaggerRoute() {
        return route("approval_service_swagger")
                .route(RequestPredicates.path("/aggregate/approval-service/v3/api-docs"),
                        HandlerFunctions.http(approvalServiceUrl))
                .filter(CircuitBreakerFilterFunctions
                        .circuitBreaker("approvalServiceSwaggerCircuitBreaker", URI.create("forward:/fallbackRoute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> fallbackRoute() {
        return route("fallbackRoute")
                .route(RequestPredicates.all(),
                        request -> ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE)
                                .body("Service is temporarily unavailable. Please try again later."))
                .build();
    }
}
