package ca.gbc.approvalservice.dto;

import ca.gbc.approvalservice.model.Approval;


public record ApprovalResponse(
        String eventId,
        String eventType,
        Approval.Status status,
        String comments
) {
}
