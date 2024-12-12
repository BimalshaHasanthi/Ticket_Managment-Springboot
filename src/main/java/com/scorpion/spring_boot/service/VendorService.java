package com.scorpion.spring_boot.service;

import com.scorpion.spring_boot.dto.SaveVendorDTO;
import com.scorpion.spring_boot.dto.VendorDTO;

import java.util.List;

public interface VendorService {
    boolean saveVendor(SaveVendorDTO vendor);
    boolean updateVendor(VendorDTO vendor);
    boolean deleteVendor(String vendorId);
    VendorDTO getVendor(String vendorId);
    List<VendorDTO> getAllVendors();
}
