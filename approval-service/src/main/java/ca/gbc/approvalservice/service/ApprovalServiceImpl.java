package ca.gbc.approvalservice.service;

import ca.gbc.approvalservice.client.EventClient;
import ca.gbc.approvalservice.client.UserClient;
import ca.gbc.approvalservice.dto.ApprovalRequest;
import ca.gbc.approvalservice.dto.ApprovalResponse;
import ca.gbc.approvalservice.model.Approval;
import ca.gbc.approvalservice.repository.ApprovalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApprovalServiceImpl implements ApprovalService {

    private final ApprovalRepository approvalRepository;
    private final UserClient userClient;
    private final EventClient eventClient;


    @Override
    public ApprovalResponse createApproval(ApprovalRequest approvalRequest) {
        var hasApproval = userClient.getUserRoleByID(approvalRequest.approverId());
        var hasEventType = eventClient.getEventType(approvalRequest.eventId());
        if (hasApproval && hasEventType != null && !hasEventType.isEmpty()) {
            Approval approval = Approval.builder()
                    .id(UUID.randomUUID().toString())
                    .eventId(approvalRequest.eventId())
                    .eventType(hasEventType)
                    .approverId(approvalRequest.approverId())
                    .status(approvalRequest.status())
                    .comments(approvalRequest.comments())
                    .build();
            approvalRepository.save(approval);
            return new ApprovalResponse(approvalRequest.eventId(), hasEventType, approvalRequest.status());
        }
        return new ApprovalResponse(approvalRequest.eventId(), hasEventType, Approval.Status.REJECTED);
    }

    @Override
    public List<ApprovalResponse> getAllApprovals() {
        return List.of();
    }

    @Override
    public ApprovalResponse updateApproval(String approvalId, ApprovalRequest approvalRequest) {
        return null;
    }

    @Override
    public String deleteApproval(String approvalId) {
        return "";
    }

}
