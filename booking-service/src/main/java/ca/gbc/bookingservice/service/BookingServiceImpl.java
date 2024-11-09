package ca.gbc.bookingservice.service;

import ca.gbc.bookingservice.dto.BookingServiceRequest;
import ca.gbc.bookingservice.dto.BookingServiceResponse;
import ca.gbc.bookingservice.model.BookingServiceModel;
import ca.gbc.bookingservice.repository.BookingServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingServiceRepository bookingRepository;

    @Override
    public BookingServiceResponse createBooking(BookingServiceRequest request) {
        BookingServiceModel booking = BookingServiceModel.builder()
            .userId(request.getUserId())
            .roomId(request.getRoomId())
            .startTime(request.getStartTime())
            .endTime(request.getEndTime())
            .purpose(request.getPurpose())
            .build();

        BookingServiceModel savedBooking = bookingRepository.save(booking);
        return mapToResponse(savedBooking);
    }

    @Override
    public List<BookingServiceResponse> getAllBookings() {
        List<BookingServiceModel> bookings = bookingRepository.findAll();
        return bookings.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public BookingServiceResponse getBookingById(String id) {
        Optional<BookingServiceModel> bookingOpt = bookingRepository.findById(id);
        if (bookingOpt.isPresent()) {
            return mapToResponse(bookingOpt.get());
        } else {
            throw new RuntimeException("Booking not found with id: " + id);
        }
    }

    @Override
    public BookingServiceResponse updateBooking(String id, BookingServiceRequest request) {
        Optional<BookingServiceModel> bookingOpt = bookingRepository.findById(id);
        if (bookingOpt.isPresent()) {
            BookingServiceModel booking = bookingOpt.get();
            booking.setUserId(request.getUserId());
            booking.setRoomId(request.getRoomId());
            booking.setStartTime(request.getStartTime());
            booking.setEndTime(request.getEndTime());
            booking.setPurpose(request.getPurpose());

            BookingServiceModel updatedBooking = bookingRepository.save(booking);
            return mapToResponse(updatedBooking);
        } else {
            throw new RuntimeException("Booking not found with id: " + id);
        }
    }

    @Override
    public void deleteBooking(String id) {
        if (bookingRepository.existsById(id)) {
            bookingRepository.deleteById(id);
        } else {
            throw new RuntimeException("Booking not found with id: " + id);
        }
    }

    private BookingServiceResponse mapToResponse(BookingServiceModel booking) {
        return BookingServiceResponse.builder()
            .id(booking.getId())
            .userId(booking.getUserId())
            .roomId(booking.getRoomId())
            .startTime(booking.getStartTime())
            .endTime(booking.getEndTime())
            .purpose(booking.getPurpose())
            .build();
    }
}