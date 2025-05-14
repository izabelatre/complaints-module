package pl.complaints.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.complaints.configuration.security.JwtService;
import pl.complaints.dao.Customer;
import pl.complaints.dto.auth.AuthRequest;
import pl.complaints.dto.auth.AuthResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email, request.password)
        );

        Customer user = (Customer) authentication.getPrincipal();
        String jwt = jwtService.generateToken(user.getEmail());

        return ResponseEntity.ok(new AuthResponse(jwt));
    }
}