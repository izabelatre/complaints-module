package pl.complaints.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.complaints.dto.ComplaintResponseDTO;
import pl.complaints.mapper.ComplaintMapper;
import pl.complaints.repository.ComplaintRepository;
import pl.complaints.service.ComplaintService;

import java.util.List;

@Service
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository complaintRepository;

    public ComplaintServiceImpl(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    @Override
    public List<ComplaintResponseDTO> getAllCustomerComplaints(Long customerId) {
        return complaintRepository.findAllByCustomerId(customerId).stream()
                .map(ComplaintMapper.INSTANCE::toDto)
                .toList();
    }
}