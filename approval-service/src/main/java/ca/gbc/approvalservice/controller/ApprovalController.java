package ca.gbc.approvalservice.controller;

import ca.gbc.approvalservice.dto.ApprovalRequest;
import ca.gbc.approvalservice.dto.ApprovalResponse;
import ca.gbc.approvalservice.service.ApprovalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/approval")
@RequiredArgsConstructor
public class ApprovalController {

    private final ApprovalService approvalService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApprovalResponse> createApproval(@RequestBody ApprovalRequest approvalRequest) {
        ApprovalResponse createApproval = approvalService.createApproval(approvalRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(createApproval);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ApprovalResponse> getAllApprovals() {
        return approvalService.getAllApprovals();

    }

    @PutMapping("/{approvalId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApprovalResponse> updateApproval(@PathVariable("approvalId") String approvalId,
                                           @RequestBody ApprovalRequest approvalRequest) {
        ApprovalResponse updatedApproval = approvalService.updateApproval(approvalId, approvalRequest);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(updatedApproval);
    }

    @DeleteMapping("/{approverId}/{approvalId}")
    public ResponseEntity<ApprovalResponse> deleteApproval(@PathVariable("approverId") String approverId,
                                                           @PathVariable("approvalId") String approvalId) {
        ApprovalResponse deletedApproval = approvalService.deleteApproval(approverId, approvalId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(deletedApproval);
    }
}
