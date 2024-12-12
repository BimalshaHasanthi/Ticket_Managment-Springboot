package com.scorpion.spring_boot.service;

import com.scorpion.spring_boot.dto.CustomerDTO;
import com.scorpion.spring_boot.dto.SaveCustomerDTO;
import com.scorpion.spring_boot.entity.Customer;
import com.scorpion.spring_boot.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public boolean saveCustomer(SaveCustomerDTO dto) {
        Customer customer = new Customer(this.getNextCustomerId(), dto.getName(), dto.getEmail(), dto.getPhoneNumber(), dto.getCustomerType());
        customerRepo.save(customer);
        return true;
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) {
        Customer customer = new Customer(dto.getCustomerId(), dto.getName(), dto.getEmail(), dto.getPhoneNumber(), dto.getCustomerType());
        customerRepo.save(customer);
        return true;
    }

    @Override
    public boolean deleteCustomer(String customerId) {
        customerRepo.deleteById(customerId);
        return true;
    }

    @Override
    public CustomerDTO getCustomer(String customerId) {
        Customer customer = customerRepo.findById(customerId).orElse(null);
        if (customer != null) {
            return new CustomerDTO(customer.getCustomerId(), customer.getName(), customer.getEmail(), customer.getPhoneNumber(), customer.getCustomerType());
        }
        return null;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepo.findAll();
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        for (Customer customer : customers) {
            customerDTOS.add(new CustomerDTO(customer.getCustomerId(), customer.getName(), customer.getEmail(), customer.getPhoneNumber(), customer.getCustomerType()));
        }
        return customerDTOS;
    }

    private String getNextCustomerId() {
        String lastCustomerId = customerRepo.getLatestId();

        if (lastCustomerId != null) {
            int index = Integer.parseInt(lastCustomerId.split("-")[1]);
            if (index < 9) {
                return "CUS-000" + ++index;
            } else if (index < 99) {
                return "CUS-00" + ++index;
            } else if (index < 999) {
                return "CUS-0" + ++index;
            } else {
                return "CUS-" + ++index;
            }
        } else {
            return "CUS-0001";
        }
    }
}
