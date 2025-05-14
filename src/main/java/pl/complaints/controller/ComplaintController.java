package pl.complaints.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.complaints.configuration.JwtService;
import pl.complaints.dto.AuthRequest;
import pl.complaints.dto.AuthResponse;
import pl.complaints.dto.ComplaintResponseDTO;
import pl.complaints.service.ComplaintService;

import java.util.List;

import static pl.complaints.utils.AuthUtils.getCustomerIdFromAuthentication;

@RestController
@RequestMapping("/complaint")
public class ComplaintController {

    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    private final ComplaintService complaintService;

    @GetMapping
    public ResponseEntity<List<ComplaintResponseDTO>> getAllUserComplains(Authentication authentication) {
        return ResponseEntity.ok( complaintService.getAllCustomerComplaints(getCustomerIdFromAuthentication(authentication)));
    }
}
