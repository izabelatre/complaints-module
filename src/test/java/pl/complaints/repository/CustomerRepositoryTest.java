package pl.complaints.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.complaints.dao.Customer;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void shouldFindCustomerByEmail() {
        Customer customer = new Customer();
        customer.setEmail("test@example.com");
        customer.setPassword("secret");
        customerRepository.save(customer);

        Optional<Customer> result = customerRepository.findByEmail("test@example.com");

        assertThat(result).isPresent();
        assertThat(result.get().getEmail()).isEqualTo("test@example.com");
    }

    @Test
    void shouldReturnEmptyIfEmailNotFound() {
        Optional<Customer> result = customerRepository.findByEmail("doesnotexist@example.com");
        assertThat(result).isEmpty();
    }
}