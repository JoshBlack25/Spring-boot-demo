package za.ac.cput.service.impl;

import za.ac.cput.domain.Customer;

public interface ICustomerService extends IService<Customer, Integer>{
    Customer findByEmail(String email);
}
