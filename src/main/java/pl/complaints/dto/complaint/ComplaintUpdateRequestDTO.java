package pl.complaints.dto.complaint;

public class ComplaintUpdateRequestDTO {
    private String description;

    public ComplaintUpdateRequestDTO(String description, Long productId) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}