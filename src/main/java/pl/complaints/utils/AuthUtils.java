package pl.complaints.utils;

import org.springframework.security.core.Authentication;
import pl.complaints.dao.Customer;

public class AuthUtils {

    public static Customer getCustomerFromAuthentication(Authentication authentication) {
        return (Customer) authentication.getPrincipal();
    }

    public static Long getCustomerIdFromAuthentication(Authentication authentication) {
        Customer customer = (Customer) authentication.getPrincipal();
        return customer.getId();
    }
}
