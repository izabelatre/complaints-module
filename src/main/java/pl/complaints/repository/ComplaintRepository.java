package pl.complaints.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.complaints.dao.Complaint;

import java.util.List;
import java.util.Optional;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    List<Complaint> findAllByCustomerId(Long customerId);

    Optional<Complaint> findByCustomerIdAndProductId(Long customerId, Long productId);

    @Modifying
    @Transactional
    @Query("UPDATE Complaint c SET c.complaintCounter = c.complaintCounter + 1 WHERE c.id = :id")
    void incrementComplaintCounter(@Param("id") Long id);
}
