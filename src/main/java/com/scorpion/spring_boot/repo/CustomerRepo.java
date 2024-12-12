package com.scorpion.spring_boot.repo;

import com.scorpion.spring_boot.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepo extends JpaRepository<Customer, String> {
    @Query(value = "SELECT customer_id FROM customer ORDER BY customer_id DESC LIMIT 1", nativeQuery = true)
    String getLatestId();
//    ArrayList<Customer> getByCustomerType(String customerType) throws SQLException, ClassNotFoundException;
}