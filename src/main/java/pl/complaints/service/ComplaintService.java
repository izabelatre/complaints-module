package pl.complaints.service;

import pl.complaints.dto.ComplaintResponseDTO;

import java.util.List;

public interface ComplaintService {
    List<ComplaintResponseDTO> getAllCustomerComplaints(Long customerId);
}
