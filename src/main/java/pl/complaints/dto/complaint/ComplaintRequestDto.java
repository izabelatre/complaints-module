package pl.complaints.dto.complaint;

public class ComplaintRequestDto {
    private String description;
    private Long productId;

    public ComplaintRequestDto(String description, Long productId) {
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