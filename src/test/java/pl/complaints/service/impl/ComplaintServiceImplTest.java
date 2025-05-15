package pl.complaints.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import pl.complaints.dao.Complaint;
import pl.complaints.dao.Customer;
import pl.complaints.dao.Product;
import pl.complaints.dto.complaint.ComplaintCreateRequestDTO;
import pl.complaints.dto.complaint.ComplaintResponseDTO;
import pl.complaints.dto.complaint.ComplaintUpdateRequestDTO;
import pl.complaints.repository.ComplaintRepository;
import pl.complaints.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ComplaintServiceImplTest {

    @Mock
    private ComplaintRepository complaintRepository;

    @Mock
    private GeoLocationService geoLocationService;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ComplaintServiceImpl complaintService;

    @Mock
    private Authentication authentication;

    @Mock
    private HttpServletRequest request;

    private final Long customerId = 1L;
    private final Long productId = 100L;

    @BeforeEach
    void cleanDatabase() {
        complaintRepository.deleteAll();
        productRepository.deleteAll();
    }


    @Test
    void shouldReturnAllCustomerComplaints() {
        Complaint complaint = new Complaint();
        complaint.setDescription("Example complaint");
        complaint.setCreatedAt(System.currentTimeMillis());
        complaint.setCountry("Poland");
        complaint.setComplaintCounter(1);

        when(complaintRepository.findAllByCustomerId(customerId)).thenReturn(List.of(complaint));

        List<ComplaintResponseDTO> result = complaintService.getAllCustomerComplaints(customerId);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getDescription()).isEqualTo("Example complaint");
    }

    @Test
    void shouldIncrementCounterIfComplaintExists() {
        Complaint existingComplaint = new Complaint();
        existingComplaint.setId(42L);

        when(authentication.getPrincipal()).thenReturn(createCustomer(customerId));
        when(complaintRepository.findByCustomerIdAndProductId(customerId, productId))
                .thenReturn(Optional.of(existingComplaint));

        ComplaintCreateRequestDTO dto = new ComplaintCreateRequestDTO("Test complaint", productId);
        complaintService.createComplaint(authentication, request, dto);

        verify(complaintRepository).incrementComplaintCounter(existingComplaint.getId());
        verify(complaintRepository, never()).save(any());
    }

    @Test
    void shouldCreateNewComplaintIfNotExists() {
        when(authentication.getPrincipal()).thenReturn(createCustomer(customerId));
        when(complaintRepository.findByCustomerIdAndProductId(customerId, productId))
                .thenReturn(Optional.empty());
        when(geoLocationService.getCountryFromRequest(request)).thenReturn("Poland");

        Product product = new Product(productId, "Laptop", "Powerful", new ArrayList<>());
        when(productRepository.getReferenceById(productId)).thenReturn(product);

        ComplaintCreateRequestDTO dto = new ComplaintCreateRequestDTO("Nowa reklamacja", productId);
        complaintService.createComplaint(authentication, request, dto);

        ArgumentCaptor<Complaint> captor = ArgumentCaptor.forClass(Complaint.class);
        verify(complaintRepository).save(captor.capture());

        Complaint saved = captor.getValue();
        assertThat(saved.getDescription()).isEqualTo("Nowa reklamacja");
        assertThat(saved.getCountry()).isEqualTo("Poland");
        assertThat(saved.getCustomer().getId()).isEqualTo(customerId);
    }

    @Test
    void shouldUpdateComplaintDescription() {
        Product product = new Product("Product", "Desc", new ArrayList<>());
        Complaint complaint = new Complaint();
        complaint.setId(5L);
        complaint.setDescription("Old description");
        complaint.setProduct(product);

        ComplaintUpdateRequestDTO dto = new ComplaintUpdateRequestDTO("Updated desc", product.getId());

        when(complaintRepository.findById(5L)).thenReturn(Optional.of(complaint));

        complaintService.updateComplaint(5L, authentication, request, dto);

        verify(complaintRepository).save(argThat(c -> c.getDescription().equals("Updated desc")));
    }

    private Customer createCustomer(Long id) {
        Customer c = new Customer();
        c.setId(id);
        c.setEmail("test@user.com");
        c.setPassword("pass");
        return c;
    }
}
