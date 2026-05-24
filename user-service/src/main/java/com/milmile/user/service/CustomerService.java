package com.milmile.user.service;

import com.milmile.user.dto.CustomerDetails;
import com.milmile.user.dto.GenreUpdateRequest;
import com.milmile.user.exception.CustomerNotFoundException;
import com.milmile.user.mapper.CustomerMapper;
import com.milmile.user.repository.CustomerRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final ApplicationEventPublisher eventPublisher;

    public CustomerService(CustomerRepository customerRepository, ApplicationEventPublisher eventPublisher) {
        this.customerRepository = customerRepository;
        this.eventPublisher = eventPublisher;
    }

    public CustomerDetails getCustomer(Integer customerId) {
        return this.customerRepository.findById(customerId)
                .map(CustomerMapper::toCustomerDetails)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));
    }

    public void updateCustomerGenre(Integer customerId, GenreUpdateRequest request) {
        var customer = this.customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));
        customer.setFavoriteGenre(request.favoriteGenre());
        this.eventPublisher.publishEvent(CustomerMapper.toGenreUpdatedEvent(customerId, request.favoriteGenre()));
    }

}
