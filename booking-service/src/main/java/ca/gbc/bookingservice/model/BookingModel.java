//package ca.gbc.bookingservice.model;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Table;
//import lombok.*;
//import org.springframework.data.annotation.Id;
//
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "bookings") // Specifies the table name in the relational database
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//public class BookingModel {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY) // Automatically generates the primary key (ID)
//	private String id; // Use Long for relational DB (auto-generated)
//
//	private String userId;
//	private String roomId;
//	private LocalDateTime startTime;
//	private LocalDateTime endTime;
//	private String purpose;
//
//	// Constructor with fields
//	public BookingModel(String userId, String roomId, LocalDateTime startTime, LocalDateTime endTime, String purpose) {
//		this.userId = userId;
//		this.roomId = roomId;
//		this.startTime = startTime;
//		this.endTime = endTime;
//		this.purpose = purpose;
//	}
//
//}

package ca.gbc.bookingservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "bookings") // Specifies the MongoDB collection name
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingModel {

	@Id
	private String id;

	private String userId;
	private String roomId;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private String purpose;

	// Constructor without generated id, for creating new documents
	public BookingModel(String userId, String roomId, LocalDateTime startTime, LocalDateTime endTime, String purpose) {
		this.userId = userId;
		this.roomId = roomId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.purpose = purpose;
	}
}
