package ca.gbc.approvalservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(value="approval")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Approval {

    @Id
    private String id;

    private String eventId;
    private String eventType;

    private UUID approverId;

    private Status status;
    private String comments;

    public enum Status {
        APPROVED,
        REJECTED
    }
}
