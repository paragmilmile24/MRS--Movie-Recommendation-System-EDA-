package com.milmile.user.mapper;

import com.milmile.user.dto.CustomerDetails;
import com.milmile.user.entity.Customer;
import events.CustomerGenreUpdatedEvent;

import java.time.Instant;

public class CustomerMapper {

    public static CustomerDetails toCustomerDetails(Customer customer){
        return new CustomerDetails(
                customer.getId(),
                customer.getName(),
                customer.getFavoriteGenre()
        );
    }

    public static CustomerGenreUpdatedEvent toGenreUpdatedEvent(Integer customerId, String favoriteGenre) {
        return new CustomerGenreUpdatedEvent(
                customerId,
                favoriteGenre,
                Instant.now()
        );
    }

}
