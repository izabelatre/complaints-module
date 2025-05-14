package pl.complaints.utils;

import org.springframework.security.core.Authentication;
import pl.complaints.dao.Customer;

public class AuthUtils {

    public static Long getCustomerIdFromAuthentication(Authentication authentication){
        Customer customer = (Customer) authentication.getPrincipal();
        return customer.getId();
    }
}
