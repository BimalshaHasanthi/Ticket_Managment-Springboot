package com.scorpion.spring_boot.controller;

import com.scorpion.spring_boot.dto.CustomerDTO;
import com.scorpion.spring_boot.dto.SaveCustomerDTO;
import com.scorpion.spring_boot.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/save")
    public ResponseEntity<String> saveCustomer(@RequestBody SaveCustomerDTO customer) {
        boolean isCustomerSaved = customerService.saveCustomer(customer);
        if (isCustomerSaved) {
            return new ResponseEntity<>("Customer saved successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Customer not saved", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCustomer(@RequestBody CustomerDTO customer) {
        boolean isCustomerUpdated = customerService.updateCustomer(customer);
        if (isCustomerUpdated) {
            return new ResponseEntity<>("Customer updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Customer not updated", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String customerId) {
        boolean isCustomerDeleted = customerService.deleteCustomer(customerId);
        if (isCustomerDeleted) {
            return new ResponseEntity<>("Customer deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Customer not deleted", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/{customerId}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable String customerId) {
        CustomerDTO customer = customerService.getCustomer(customerId);
        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> customers = customerService.getAllCustomers();
        if (customers != null) {
            return new ResponseEntity<>(customers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
