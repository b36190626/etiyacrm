package com.etiya.searchservice.kafka.customers;

import com.etiya.common.events.customers.CustomerDeletedEvent;
import com.etiya.searchservice.entities.Customer;
import com.etiya.searchservice.repository.FilterRepository;
import com.etiya.searchservice.service.abstracts.FilterService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class CustomerDeletedConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerCreatedConsumer.class);
    private FilterService filterService;
    private FilterRepository filterRepository;

    @KafkaListener(topics = "customer-deleted", groupId = "delete-customer")
    private void consume(CustomerDeletedEvent customerDeletedEvent){
        Customer customer = filterRepository.findById(customerDeletedEvent.getId()).get();
        customer.setId(customerDeletedEvent.getId());
        customer.setDeletedDate(LocalDateTime.now());

        LOGGER.info(String.format("Customer deleted event consumer => %s", customerDeletedEvent.toString()));
        this.filterService.deleteCustomer(customer);
    }
}
