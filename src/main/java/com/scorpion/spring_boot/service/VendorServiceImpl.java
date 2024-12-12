package com.scorpion.spring_boot.service;

import com.scorpion.spring_boot.dto.SaveVendorDTO;
import com.scorpion.spring_boot.dto.VendorDTO;
import com.scorpion.spring_boot.entity.Vendor;
import com.scorpion.spring_boot.repo.VendorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class VendorServiceImpl implements VendorService {
    @Autowired
    private VendorRepo vendorRepo;

    @Override
    public boolean saveVendor(SaveVendorDTO dto) {
        Vendor vendor = new Vendor(this.getNextVendorId(), dto.getName(), dto.getEmail(), dto.getPhoneNumber());
        vendorRepo.save(vendor);
        return true;
    }

    @Override
    public boolean updateVendor(VendorDTO dto) {
        Vendor vendor = new Vendor(dto.getVendorId(), dto.getName(), dto.getEmail(), dto.getPhoneNumber());
        vendorRepo.save(vendor);
        return true;
    }

    @Override
    public boolean deleteVendor(String vendorId) {
        vendorRepo.deleteById(vendorId);
        return true;
    }

    @Override
    public VendorDTO getVendor(String vendorId) {
        Vendor vendor = vendorRepo.findById(vendorId).orElse(null);
        if (vendor != null) {
            return new VendorDTO(vendor.getVendorId(), vendor.getName(), vendor.getEmail(), vendor.getPhoneNumber());
        }
        return null;
    }

    @Override
    public List<VendorDTO> getAllVendors() {
        List<Vendor> vendors = vendorRepo.findAll();
        List<VendorDTO> vendorDTOS = new ArrayList<>();
        for (Vendor vendor : vendors) {
            vendorDTOS.add(new VendorDTO(vendor.getVendorId(), vendor.getName(), vendor.getEmail(), vendor.getPhoneNumber()));
        }
        return vendorDTOS;
    }

    private  String getNextVendorId() {
        String lastVendorId = vendorRepo.getLatestId();

        if (lastVendorId != null) {
            int index = Integer.parseInt(lastVendorId.split("-")[1]);
            if (index < 9) {
                return "VEN-000" + ++index;
            } else if (index < 99) {
                return "VEN-00" + ++index;
            } else if (index < 999) {
                return "VEN-0" + ++index;
            } else {
                return "VEN-" + ++index;
            }
        } else {
            return "VEN-0001";
        }
    }
}
