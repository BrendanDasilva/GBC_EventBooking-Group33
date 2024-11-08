package ca.gbc.approvalservice.dto;

import ca.gbc.approvalservice.model.Approval;

import java.util.UUID;

public record ApprovalResponse(
        String eventId,
        String eventType,
        Approval.Status status
) {
}
