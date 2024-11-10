package ca.gbc.bookingservice.service;

import ca.gbc.bookingservice.dto.BookingServiceRequest;
import ca.gbc.bookingservice.model.BookingModel;
import ca.gbc.bookingservice.repository.BookingServiceRepository;
import ca.gbc.roomservice.service.RoomService;
import ca.gbc.bookingservice.exception.RoomNotAvailableException;
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
        // Step 1: Check room availability using RoomService
        boolean isRoomAvailable = roomService.checkRoomAvailability("Room" + bookingRequest.getRoomId());

        if (!isRoomAvailable) {
            throw new RoomNotAvailableException("The room is not available for the selected dates.");
        }

        // Step 2: Convert the DTO to Entity (BookingModel)
        BookingModel booking = bookingRequest.toEntity();

        // Step 3: Save the booking to the repository
        return bookingRepository.save(booking);
    }

    @Override
    public List<BookingModel> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public BookingModel updateBooking(Long bookingId, BookingServiceRequest updatedRequest) throws RoomNotAvailableException {
        // Step 1: Check if the booking exists
        Optional<BookingModel> existingBooking = bookingRepository.findById(String.valueOf(bookingId));

        if (!existingBooking.isPresent()) {
            throw new RoomNotAvailableException("Booking not found.");
        }

        // Step 2: Check room availability for the updated booking
        boolean isRoomAvailable = roomService.checkRoomAvailability("Room" + updatedRequest.getRoomId());

        if (!isRoomAvailable) {
            throw new RoomNotAvailableException("The room is not available for the selected dates.");
        }

        // Step 3: Update the existing booking with the new data
        BookingModel existingBookingModel = existingBooking.get();
        existingBookingModel.setUserId(updatedRequest.getUserId());
        existingBookingModel.setRoomId(updatedRequest.getRoomId());
        existingBookingModel.setStartTime(updatedRequest.getStartTime());
        existingBookingModel.setEndTime(updatedRequest.getEndTime());
        existingBookingModel.setPurpose(updatedRequest.getPurpose());

        // Step 4: Save the updated booking
        return bookingRepository.save(existingBookingModel);
    }

    @Override
    public void deleteBooking(Long bookingId) throws RoomNotAvailableException {
        // Step 1: Check if the booking exists
        Optional<BookingModel> existingBooking = bookingRepository.findById(String.valueOf(bookingId));
        if (!existingBooking.isPresent()) {
            throw new RoomNotAvailableException("Booking not found.");
        }

        // Step 2: Delete the booking
        bookingRepository.deleteById(String.valueOf(bookingId));
    }
}
