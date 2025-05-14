package pl.complaints.dto;

import lombok.Data;

@Data
public class AuthRequest {
    public String email;
    public String password;
}
