package ca.gbc.roomservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "rooms")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomServiceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String roomName;

    @Column(nullable = false)
    private int capacity;

    @ElementCollection
    @CollectionTable(name = "room_features", joinColumns = @JoinColumn(name = "room_id"))
    @Column(name = "feature")
    private List<String> features;

    @Column(nullable = false)
    private boolean availability;
}