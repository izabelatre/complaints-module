package pl.complaints.initialization;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.complaints.dao.Complaint;
import pl.complaints.dao.Customer;
import pl.complaints.dao.Product;
import pl.complaints.repository.ComplaintRepository;
import pl.complaints.repository.CustomerRepository;
import pl.complaints.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final ComplaintRepository complaintRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(CustomerRepository customerRepository, ProductRepository productRepository,
                           ComplaintRepository complaintRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.complaintRepository = complaintRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        String defaultEmail = "admin@example.com";
        Customer customer = customerRepository.findByEmail(defaultEmail)
                .orElseGet(() -> {
                    Customer newCustomer = new Customer();
                    newCustomer.setEmail(defaultEmail);
                    newCustomer.setPassword(passwordEncoder.encode("admin123"));
                    return customerRepository.save(newCustomer);
                });

        // Products
        if (productRepository.count() == 0) {
            Product product1 = new Product(null, "Laptop Dell XPS 13", "Ultrabook z ekranem 13\"", new ArrayList<>());
            Product product2 = new Product(null, "Smartfon Galaxy S21", "Flagowy telefon Samsunga", new ArrayList<>());
            Product product3 = new Product(null, "Bose QC45", "Bezprzewodowe słuchawki z ANC", new ArrayList<>());

            productRepository.saveAll(List.of(product1, product2, product3));
        }

        // Complaints
        if (complaintRepository.count() == 0) {
            List<Product> products = productRepository.findAll();

            Complaint complaint1 = new Complaint();
            complaint1.setDescription("Ekran migocze podczas pracy na baterii.");
            complaint1.setCreatedAt(System.currentTimeMillis());
            complaint1.setComplaintCounter(1);
            complaint1.setCustomer(customer);
            complaint1.setProduct(products.get(0));

            Complaint complaint2 = new Complaint();
            complaint2.setDescription("Telefon przegrzewa się podczas ładowania.");
            complaint2.setCreatedAt(System.currentTimeMillis());
            complaint2.setComplaintCounter(1);
            complaint2.setCustomer(customer);
            complaint2.setProduct(products.get(1));

            complaintRepository.saveAll(List.of(complaint1, complaint2));
        }
    }
}
