package ca.gbc.approvalservice.service;

import ca.gbc.approvalservice.client.EventClient;
import ca.gbc.approvalservice.client.UserClient;
import ca.gbc.approvalservice.dto.ApprovalRequest;
import ca.gbc.approvalservice.dto.ApprovalResponse;
import ca.gbc.approvalservice.model.Approval;
import ca.gbc.approvalservice.repository.ApprovalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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
    private final MongoTemplate mongoTemplate;


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
            return new ApprovalResponse(approvalRequest.eventId(), hasEventType,
                    approvalRequest.status(), approvalRequest.comments());
        }
        return new ApprovalResponse(approvalRequest.eventId(), hasEventType,
                Approval.Status.REJECTED, "Authentication Rejected");
    }

    @Override
    public List<ApprovalResponse> getAllApprovals() {
        List<Approval> approvals = approvalRepository.findAll();
        return approvals.stream().map(this::mapToApprovalResponse).toList();
    }

    private ApprovalResponse mapToApprovalResponse(Approval approval) {
        return new ApprovalResponse(approval.getEventId(), approval.getEventType(),
                approval.getStatus(), approval.getComments());
    }

    @Override
    public ApprovalResponse updateApproval(String approvalId, ApprovalRequest approvalRequest) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(approvalId));
        Approval approval = mongoTemplate.findOne(query, Approval.class);
        var hasApproval = userClient.getUserRoleByID(approvalRequest.approverId());
        var hasEventType = eventClient.getEventType(approvalRequest.eventId());
        if (approval != null && hasApproval && hasEventType != null && !hasEventType.isEmpty()) {
            approval.setEventId(approvalRequest.eventId());
            approval.setEventType(hasEventType);
            approval.setApproverId(approvalRequest.approverId());
            approval.setStatus(approvalRequest.status());
            approval.setComments(approvalRequest.comments());
            approvalRepository.save(approval);
            return new ApprovalResponse(approval.getEventId(), hasEventType,
                    approval.getStatus(), approval.getComments());
        } else {
            return new ApprovalResponse(approvalRequest.eventId(), hasEventType,
                    Approval.Status.REJECTED, "Authentication Rejected");
        }
    }

    @Override
    public ApprovalResponse deleteApproval(String approverId, String approvalId) {
        var hasApproval = userClient.getUserRoleByID(approverId);
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(approvalId));
        Approval approval = mongoTemplate.findOne(query, Approval.class);
        if (approval != null && hasApproval) {
            approvalRepository.deleteById(approvalId);
            return new ApprovalResponse(approval.getEventId(), approval.getEventType(),
                    approval.getStatus(), "Approval Request Deleted");

        }
        assert approval != null;
        return new ApprovalResponse(approval.getEventId(), approval.getEventType(),
                Approval.Status.REJECTED, "Authentication Rejected");
    }


}
