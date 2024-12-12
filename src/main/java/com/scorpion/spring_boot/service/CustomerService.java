package com.scorpion.spring_boot.service;

import com.scorpion.spring_boot.dto.CustomerDTO;
import com.scorpion.spring_boot.dto.SaveCustomerDTO;

import java.util.List;

public interface CustomerService {
    boolean saveCustomer(SaveCustomerDTO customer);
    boolean updateCustomer(CustomerDTO customer);
    boolean deleteCustomer(String customerId);
    CustomerDTO getCustomer(String customerId);
    List<CustomerDTO> getAllCustomers();
}
