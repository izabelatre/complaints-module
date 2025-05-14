package pl.complaints.dto.auth;

import lombok.Data;

@Data
public class AuthRequest {
    public String email;
    public String password;
}
