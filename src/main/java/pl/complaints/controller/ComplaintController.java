package pl.complaints.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.complaints.dto.complaint.ComplaintCreateRequestDTO;
import pl.complaints.dto.complaint.ComplaintResponseDTO;
import pl.complaints.dto.complaint.ComplaintUpdateRequestDTO;
import pl.complaints.service.ComplaintService;

import java.util.List;

import static pl.complaints.utils.AuthUtils.getCustomerIdFromAuthentication;

@RestController
@RequestMapping("/complaint")
public class ComplaintController {

    private final ComplaintService complaintService;

    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @GetMapping
    public ResponseEntity<List<ComplaintResponseDTO>> getAllUserComplaints(Authentication authentication, HttpServletRequest request) {
        return ResponseEntity.ok(complaintService.getAllCustomerComplaints(getCustomerIdFromAuthentication(authentication)));
    }

    @PostMapping
    public ResponseEntity<Boolean> createComplaint(Authentication authentication, HttpServletRequest request,
                                                   @RequestBody ComplaintCreateRequestDTO complaintCreateRequestDto) {
        complaintService.createComplaint(authentication, request, complaintCreateRequestDto);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Boolean> editComplaint(Authentication authentication, HttpServletRequest request,
                                                 @RequestBody ComplaintUpdateRequestDTO complaintCreateRequestDto,
                                                 @PathVariable Long id) {
        complaintService.updateComplaint(id,authentication, request, complaintCreateRequestDto);
        return ResponseEntity.ok(Boolean.TRUE);
    }
}