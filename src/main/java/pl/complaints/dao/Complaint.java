package pl.complaints.dao;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Builder
@Table(name = "complaints", uniqueConstraints = {@UniqueConstraint(columnNames = {"customer_id", "product_id"})})
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private long createdAt;
    private String country;
    @Builder.Default
    private int complaintCounter = 1;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public Complaint(String description, String country, Customer customer, Product product) {
        this.description = description;
        this.customer = customer;
        this.product = product;
        this.createdAt = System.currentTimeMillis();
        this.country = country;
        this.complaintCounter = 1;
    }

    public Complaint() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getComplaintCounter() {
        return complaintCounter;
    }

    public void setComplaintCounter(int complaintCounter) {
        this.complaintCounter = complaintCounter;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
