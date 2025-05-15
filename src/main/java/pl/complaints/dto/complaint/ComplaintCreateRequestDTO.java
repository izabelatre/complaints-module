package pl.complaints.dto.complaint;

public class ComplaintCreateRequestDTO {
    private String description;
    private Long productId;

    public ComplaintCreateRequestDTO(String description, Long productId) {
        this.description = description;
        this.productId = productId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}