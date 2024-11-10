package ca.gbc.bookingservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "bookings") // Specifies the table name in the relational database
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-increment for numeric primary keys
    @Column(name = "id")  // Optional: specify the column name if different from the field name
    private Long id;  // Primary key

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "room_id", nullable = false)
    private Long roomId;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @Column(name = "purpose")
    private String purpose;

    // Constructor with fields
    public BookingModel(Long userId, Long roomId, LocalDateTime startTime, LocalDateTime endTime, String purpose) {
        this.userId = Long.valueOf(String.valueOf(userId));
        this.roomId = Long.valueOf(String.valueOf(roomId));
        this.startTime = startTime;
        this.endTime = endTime;
        this.purpose = purpose;
    }


}
