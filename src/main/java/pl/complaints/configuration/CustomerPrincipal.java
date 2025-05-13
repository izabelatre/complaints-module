package pl.complaints.configuration;

import org.springframework.security.core.userdetails.UserDetails;
import pl.complaints.dao.Customer;

public class CustomerPrincipal implements UserDetails {

	private final Customer customer;

	public CustomerPrincipal(Customer customer) {
		this.customer = customer;
	}

	public Long getCustomerId() {
		return customer.getId();
	}

}
