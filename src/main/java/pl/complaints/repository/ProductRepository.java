package pl.complaints.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.complaints.dao.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
