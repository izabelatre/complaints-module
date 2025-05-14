package pl.complaints.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pl.complaints.dao.Complaint;
import pl.complaints.dto.complaint.ComplaintRequestDto;
import pl.complaints.dto.complaint.ComplaintResponseDTO;
import pl.complaints.mapper.ComplaintMapper;
import pl.complaints.repository.ComplaintRepository;
import pl.complaints.repository.ProductRepository;
import pl.complaints.service.ComplaintService;

import java.util.List;
import java.util.Optional;

import static pl.complaints.utils.AuthUtils.getCustomerFromAuthentication;
import static pl.complaints.utils.AuthUtils.getCustomerIdFromAuthentication;

@Service
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final GeoLocationService geoLocationService;
    private final ProductRepository productRepository;

    public ComplaintServiceImpl(ComplaintRepository complaintRepository, GeoLocationService geoLocationService, ProductRepository productRepository) {
        this.complaintRepository = complaintRepository;
        this.geoLocationService = geoLocationService;
        this.productRepository = productRepository;
    }

    @Override
    public List<ComplaintResponseDTO> getAllCustomerComplaints(Long customerId) {
        return complaintRepository.findAllByCustomerId(customerId).stream()
                .map(ComplaintMapper.INSTANCE::toDto)
                .toList();
    }

    @Override
    public void createComplain(Authentication authentication, HttpServletRequest request,
                               ComplaintRequestDto complaintRequestDto) {

        Long customerId = getCustomerIdFromAuthentication(authentication);
        Long productId = complaintRequestDto.getProductId();

        Optional<Complaint> existingComplaint = complaintRepository.findByCustomerIdAndProductId(customerId, productId);

        if (existingComplaint.isPresent()) {
            complaintRepository.incrementComplaintCounter(existingComplaint.get().getId());
            return;
        }

        Complaint newComplaint = new Complaint(
                complaintRequestDto.getDescription(),
                geoLocationService.getCountryFromRequest(request),
                getCustomerFromAuthentication(authentication),
                productRepository.getReferenceById(productId)
        );

        complaintRepository.save(newComplaint);
    }
}