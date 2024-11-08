package ca.gbc.approvalservice.service;

import ca.gbc.approvalservice.client.UserClient;
import ca.gbc.approvalservice.dto.ApprovalRequest;
import ca.gbc.approvalservice.dto.ApprovalResponse;
import ca.gbc.approvalservice.repository.ApprovalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;



import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApprovalServiceImpl implements ApprovalService {

    private final ApprovalRepository approvalRepository;
    private final UserClient userClient;


    @Override
    public ApprovalResponse createApproval(ApprovalRequest approvalRequest) {
        return null;
    }

    @Override
    public List<ApprovalResponse> getAllApprovals() {
        return List.of();
    }

    @Override
    public ApprovalResponse updateApproval(String approvalId, ApprovalRequest approvalRequest) {
        return null;
    }

}
