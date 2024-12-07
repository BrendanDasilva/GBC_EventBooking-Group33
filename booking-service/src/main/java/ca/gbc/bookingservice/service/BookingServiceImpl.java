package ca.gbc.bookingservice.service;

import ca.gbc.bookingservice.dto.BookingServiceRequest;
import ca.gbc.bookingservice.model.BookingModel;
import ca.gbc.bookingservice.exception.RoomNotAvailableException;
import ca.gbc.bookingservice.repository.BookingServiceRepository;
import ca.gbc.roomservice.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    private final RoomService roomService;
    private final BookingServiceRepository bookingRepository;

    @Autowired
    public BookingServiceImpl(RoomService roomService, BookingServiceRepository bookingRepository) {
        this.roomService = roomService;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public BookingModel createBooking(BookingServiceRequest bookingRequest) throws RoomNotAvailableException {
        boolean isRoomAvailable = roomService.checkRoomAvailability("Room" + bookingRequest.roomId());

        if (!isRoomAvailable) {
            throw new RoomNotAvailableException("The room is not available for the selected dates.");
        }

        BookingModel booking = BookingModel.builder()
                .userId(String.valueOf(bookingRequest.userId()))
                .roomId(String.valueOf(bookingRequest.roomId()))
                .startTime(bookingRequest.startTime())
                .endTime(bookingRequest.endTime())
                .purpose(bookingRequest.purpose())
                .email(bookingRequest.userDetails().email())
                .firstName(bookingRequest.userDetails().firstName())
                .lastName(bookingRequest.userDetails().lastName())
                .build();

        return bookingRepository.save(booking);
    }

    @Override
    public List<BookingModel> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public BookingModel updateBooking(String bookingId, BookingServiceRequest updatedRequest) throws RoomNotAvailableException {
        Optional<BookingModel> existingBooking = bookingRepository.findById(bookingId);

        if (existingBooking.isEmpty()) {
            throw new RoomNotAvailableException("Booking not found.");
        }

        boolean isRoomAvailable = roomService.checkRoomAvailability("Room" + updatedRequest.roomId());

        if (!isRoomAvailable) {
            throw new RoomNotAvailableException("The room is not available for the selected dates.");
        }

        BookingModel existingBookingModel = existingBooking.get();
        existingBookingModel.setUserId(String.valueOf(updatedRequest.userId()));
        existingBookingModel.setRoomId(String.valueOf(updatedRequest.roomId()));
        existingBookingModel.setStartTime(updatedRequest.startTime());
        existingBookingModel.setEndTime(updatedRequest.endTime());
        existingBookingModel.setPurpose(updatedRequest.purpose());
        existingBookingModel.setEmail(updatedRequest.userDetails().email());
        existingBookingModel.setFirstName(updatedRequest.userDetails().firstName());
        existingBookingModel.setLastName(updatedRequest.userDetails().lastName());

        return bookingRepository.save(existingBookingModel);
    }

    @Override
    public void deleteBooking(String bookingId) throws RoomNotAvailableException {
        if (!bookingRepository.existsById(bookingId)) {
            throw new RoomNotAvailableException("Booking not found.");
        }
        bookingRepository.deleteById(bookingId);
    }
}
