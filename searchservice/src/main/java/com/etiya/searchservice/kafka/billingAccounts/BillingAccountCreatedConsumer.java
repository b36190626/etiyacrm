package com.etiya.searchservice.kafka.billingAccounts;

import com.etiya.common.events.billingAccounts.BillingAccountCreatedEvent;
import com.etiya.searchservice.entities.Customer;
import com.etiya.searchservice.kafka.contactMediums.ContactMediumUpdatedConsumer;
import com.etiya.searchservice.service.abstracts.FilterService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BillingAccountCreatedConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(BillingAccountCreatedConsumer.class);
    private FilterService filterService;

    @KafkaListener(topics = "billing-account-created", groupId = "create-billing-account")
    private void consume(BillingAccountCreatedEvent billingAccountCreatedEvent){
        Customer customer = filterService.getById(billingAccountCreatedEvent.getId());
        customer.setId(billingAccountCreatedEvent.getId());
        customer.setAccountNumber(billingAccountCreatedEvent.getAccountNumber());

        LOGGER.info(String.format("Billing Account created event consumer => %s", billingAccountCreatedEvent.toString()));
        this.filterService.updateCustomer(customer);
    }
}
