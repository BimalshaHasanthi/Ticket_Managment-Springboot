package com.scorpion.spring_boot.repo;

import com.scorpion.spring_boot.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VendorRepo extends JpaRepository<Vendor, String> {
    @Query(value = "SELECT vendor_id FROM vendor ORDER BY vendor_id DESC LIMIT 1", nativeQuery = true)
    String getLatestId();
}
