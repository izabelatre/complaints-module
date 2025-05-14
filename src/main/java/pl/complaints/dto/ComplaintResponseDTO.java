package pl.complaints.dto;

public class ComplaintResponseDTO {

    private String description;
    private long createdAt;
    private String productName;

    public ComplaintResponseDTO(String description, long createdAt, String productName) {
        this.description = description;
        this.createdAt = createdAt;
        this.productName = productName;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}