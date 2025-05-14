package pl.complaints.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.complaints.dto.complaint.ComplaintRequestDto;
import pl.complaints.dto.complaint.ComplaintResponseDTO;
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
    public ResponseEntity<List<ComplaintResponseDTO>> getAllUserComplains(Authentication authentication,
                                                                          HttpServletRequest request) {
        return ResponseEntity.ok(complaintService.getAllCustomerComplaints(getCustomerIdFromAuthentication(authentication)));
    }

    @PostMapping
    public ResponseEntity<Boolean> createComplain(Authentication authentication,
                                                  HttpServletRequest request,
                                                  @RequestBody ComplaintRequestDto complaintRequestDto) {
        complaintService.createComplain(authentication, request, complaintRequestDto);
        return ResponseEntity.ok(Boolean.TRUE);
    }

//    @PatchMapping("/{id}")
//    public ResponseEntity<ComplaintResponseDTO> editComplain(Authentication authentication, HttpServletRequest request) {
//        return ResponseEntity.ok(complaintService.getAllCustomerComplaints(getCustomerIdFromAuthentication(authentication)));
//    }
}
