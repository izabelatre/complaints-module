package pl.complaints.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.complaints.dao.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Optional<Customer> findByEmail(String email);

}