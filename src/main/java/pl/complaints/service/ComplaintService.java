package pl.complaints.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import pl.complaints.dto.complaint.ComplaintRequestDto;
import pl.complaints.dto.complaint.ComplaintResponseDTO;

import java.util.List;

public interface ComplaintService {

    List<ComplaintResponseDTO> getAllCustomerComplaints(Long customerId);

    void createComplain(Authentication authentication, HttpServletRequest request, ComplaintRequestDto complaintRequestDto);
}
