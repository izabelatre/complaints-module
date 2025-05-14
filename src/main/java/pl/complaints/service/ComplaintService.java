package pl.complaints.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import pl.complaints.dto.complaint.ComplaintCreateRequestDTO;
import pl.complaints.dto.complaint.ComplaintResponseDTO;
import pl.complaints.dto.complaint.ComplaintUpdateRequestDTO;

import java.util.List;

public interface ComplaintService {

    List<ComplaintResponseDTO> getAllCustomerComplaints(Long customerId);

    void createComplaint(Authentication authentication, HttpServletRequest request,
                         ComplaintCreateRequestDTO complaintCreateRequestDto);

    void updateComplaint(Long id, Authentication authentication, HttpServletRequest request,
                         ComplaintUpdateRequestDTO complaintUpdateRequestDTO);

}
