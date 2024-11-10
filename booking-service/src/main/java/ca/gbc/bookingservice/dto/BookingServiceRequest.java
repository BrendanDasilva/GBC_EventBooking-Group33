package ca.gbc.bookingservice.dto;

import ca.gbc.bookingservice.model.BookingModel;
import ca.gbc.bookingservice.repository.BookingServiceRepository;
import ca.gbc.bookingservice.exception.RoomNotAvailableException;
import java.util.Optional;
import java.time.LocalDateTime;

public class BookingServiceRequest {

    private Long userId;
    private Long roomId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String purpose;

    // Constructors
    public BookingServiceRequest() {}

    public BookingServiceRequest(Long userId, Long roomId, LocalDateTime startTime, LocalDateTime endTime, String purpose) {
        this.userId = userId;
        this.roomId = roomId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.purpose = purpose;
    }

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    // Convert DTO to Entity
    public BookingModel toEntity() {
        return BookingModel.builder()
                .userId(Long.valueOf(String.valueOf(this.userId)))
                .roomId(Long.valueOf(String.valueOf(this.roomId)))
                .startTime(this.startTime)
                .endTime(this.endTime)
                .purpose(this.purpose)
                .build();
    }

    // CRUD Operations (placeholders, typically should be in service class)

    // 1. Create a new booking (should actually be in the service layer)
    public static BookingModel createBooking(BookingServiceRequest bookingRequest, BookingServiceRepository bookingRepository) throws RoomNotAvailableException {
        BookingModel booking = bookingRequest.toEntity();
        // Logic for room availability and creation
        return bookingRepository.save(booking);
    }

    // 2. Get an existing booking (usually using ID)
    public static Optional<BookingModel> getBooking(Long bookingId, BookingServiceRepository bookingRepository) {
        return bookingRepository.findById(String.valueOf(bookingId));
    }

    // 3. Update an existing booking
    public static BookingModel updateBooking(Long bookingId, BookingServiceRequest updatedRequest, BookingServiceRepository bookingRepository) throws RoomNotAvailableException {
        Optional<BookingModel> existingBookingOpt = bookingRepository.findById(String.valueOf(bookingId));

        if (existingBookingOpt.isEmpty()) {
            throw new RoomNotAvailableException("Booking not found.");
        }

        BookingModel existingBooking = existingBookingOpt.get();
        existingBooking.setUserId(updatedRequest.getUserId());
        existingBooking.setRoomId(updatedRequest.getRoomId());
        existingBooking.setStartTime(updatedRequest.getStartTime());
        existingBooking.setEndTime(updatedRequest.getEndTime());
        existingBooking.setPurpose(updatedRequest.getPurpose());

        return bookingRepository.save(existingBooking);
    }

    // 4. Delete an existing booking
    public static void deleteBooking(Long bookingId, BookingServiceRepository bookingRepository) throws RoomNotAvailableException {
        Optional<BookingModel> existingBookingOpt = bookingRepository.findById(String.valueOf(bookingId));

        if (existingBookingOpt.isEmpty()) {
            throw new RoomNotAvailableException("Booking not found.");
        }

        bookingRepository.deleteById(String.valueOf(bookingId));
    }
}
