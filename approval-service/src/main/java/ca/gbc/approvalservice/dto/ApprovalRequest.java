package ca.gbc.approvalservice.dto;

import ca.gbc.approvalservice.model.Approval;


public record ApprovalRequest(
        String id,
        String eventId,
        String approverId,
        Approval.Status status,
        String comments
) {
}
