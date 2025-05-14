package pl.complaints.exception;

public class ComplaintNotFoundException extends RuntimeException {
    public ComplaintNotFoundException(Long id) {
        super("Complaint with ID " + id + " not found");
    }
}