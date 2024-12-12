package com.scorpion.spring_boot.controller;

import com.scorpion.spring_boot.dto.SaveVendorDTO;
import com.scorpion.spring_boot.dto.VendorDTO;
import com.scorpion.spring_boot.service.VendorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/vendor")
public class VendorController {
    @Autowired
    private VendorService vendorService;

    @PostMapping("/save")
    public ResponseEntity<String> saveVendor(@RequestBody SaveVendorDTO vendor) {
        boolean isVendorSaved = vendorService.saveVendor(vendor);
        if (isVendorSaved) {
            return new ResponseEntity<>("Vendor saved successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Vendor not saved", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateVendor(@RequestBody VendorDTO vendor) {
        boolean isVendorUpdated = vendorService.updateVendor(vendor);
        if (isVendorUpdated) {
            return new ResponseEntity<>("Vendor updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Vendor not updated", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{vendorId}")
    public ResponseEntity<String> deleteVendor(@PathVariable String vendorId) {
        boolean isVendorDeleted = vendorService.deleteVendor(vendorId);
        if (isVendorDeleted) {
            return new ResponseEntity<>("Vendor deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Vendor not deleted", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/{vendorId}")
    public ResponseEntity<VendorDTO> getVendor(@PathVariable String vendorId) {
        VendorDTO vendor = vendorService.getVendor(vendorId);
        if (vendor != null) {
            return new ResponseEntity<>(vendor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<VendorDTO>> getAllVendors() {
        List<VendorDTO> vendors = vendorService.getAllVendors();
        if (vendors != null) {
            return new ResponseEntity<>(vendors, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
