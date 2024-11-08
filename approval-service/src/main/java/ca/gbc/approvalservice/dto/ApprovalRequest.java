package ca.gbc.approvalservice.dto;

import ca.gbc.approvalservice.model.Approval;

import java.util.UUID;

public record ApprovalRequest(
        String id,

        UUID eventId,
        UUID approverId,

        Approval.Status status,
        String comments
) {
}
