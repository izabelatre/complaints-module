package pl.complaints.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import pl.complaints.dao.Complaint;
import pl.complaints.dao.Customer;
import pl.complaints.dao.Product;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ComplaintRepositoryTest {

    @Autowired
    private ComplaintRepository complaintRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void cleanDatabase() {
        complaintRepository.deleteAll();
        customerRepository.deleteAll();
        productRepository.deleteAll();
    }

    @Test
    void shouldFindAllComplaintsByCustomerId() {
        Customer customer = new Customer();
        customer.setEmail("test@example.com");
        customer.setPassword("password");
        customer = customerRepository.save(customer);

        Product product = new Product("prod", "desc", List.of());
        productRepository.save(product);

        Complaint complaint = new Complaint();
        complaint.setCustomer(customer);
        complaint.setDescription("Reklamacja A");
        complaint.setCreatedAt(System.currentTimeMillis());
        complaint.setCountry("PL");
        complaint.setComplaintCounter(1);
        complaint.setProduct(product);
        complaintRepository.save(complaint);

        List<Complaint> complaints = complaintRepository.findAllByCustomerId(customer.getId());

        assertThat(complaints).hasSize(1);
        assertThat(complaints.get(0).getDescription()).isEqualTo("Reklamacja A");
    }

    @Test
    void shouldFindByCustomerIdAndProductId() {
        Customer customer = customerRepository.save(new Customer("a@b.com", "pass", List.of()));
        Product product = new Product(null, "prod", "desc", List.of());
        entityManager.persist(product);

        Complaint complaint = new Complaint("desc", "PL", customer, product);
        complaintRepository.save(complaint);

        Optional<Complaint> result = complaintRepository.findByCustomerIdAndProductId(customer.getId(), product.getId());

        assertThat(result).isPresent();
        assertThat(result.get().getDescription()).isEqualTo("desc");
    }

    @Test
    @Transactional
    void shouldIncrementComplaintCounter() {
        //GIVEN
        Customer customer = customerRepository.save(new Customer("x@y.com", "pass", List.of()));
        Product product = new Product(null, "prod", "desc", List.of());
        entityManager.persist(product);
        entityManager.flush();

        Complaint complaint = new Complaint("desc", "PL", customer, product);
        complaint = complaintRepository.save(complaint);
        entityManager.flush();

        //WHEN
        complaintRepository.incrementComplaintCounter(complaint.getId());
        entityManager.flush();
        entityManager.clear();

        //THEN
        Complaint updated = complaintRepository.findById(complaint.getId()).orElseThrow();
        assertThat(updated.getComplaintCounter()).isEqualTo(2);

        //WHEN
        complaintRepository.incrementComplaintCounter(complaint.getId());
        entityManager.flush();
        entityManager.clear();

        //WHEN
        updated = complaintRepository.findById(complaint.getId()).orElseThrow();
        assertThat(updated.getComplaintCounter()).isEqualTo(3);
    }
}