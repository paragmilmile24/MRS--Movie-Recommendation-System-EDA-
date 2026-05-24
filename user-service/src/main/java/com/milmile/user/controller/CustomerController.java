package com.milmile.user.controller;

import com.milmile.user.dto.CustomerDetails;
import com.milmile.user.dto.GenreUpdateRequest;
import com.milmile.user.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDetails> getCustomer(@PathVariable Integer customerId){
        var customerDetails = this.customerService.getCustomer(customerId);
        return ResponseEntity.ok(customerDetails);
    }

    @PatchMapping("/{customerId}/genre")
    public ResponseEntity<Void> updateGenre(@PathVariable Integer customerId, @RequestBody GenreUpdateRequest request){
        this.customerService.updateCustomerGenre(customerId, request);
        return ResponseEntity.noContent().build(); // 204
    }
}
