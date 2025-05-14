package pl.complaints.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.complaints.dao.Complaint;
import pl.complaints.dao.Customer;
import pl.complaints.dao.Product;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

	List<Complaint> findAllByCustomerId(Long customerId);
}
