package pl.complaints.dao;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "product")
    private List<Complaint> complaints = new ArrayList<>();

    public Product(String name, String description, List<Complaint> complaints) {
        this.name = name;
        this.description = description;
        this.complaints = complaints;
    }

    public Product(Long id, String name, String description, List<Complaint> complaints) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.complaints = complaints;
    }

    public Product() {
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}