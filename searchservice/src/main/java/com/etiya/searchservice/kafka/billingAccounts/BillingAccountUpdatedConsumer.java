package com.etiya.searchservice.kafka.billingAccounts;

import com.etiya.common.events.billingAccounts.BillingAccountUpdatedEvent;
import com.etiya.searchservice.entities.Customer;
import com.etiya.searchservice.service.abstracts.FilterService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BillingAccountUpdatedConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(BillingAccountUpdatedConsumer.class);
    private FilterService filterService;

    @KafkaListener(topics = "billing-account-updated", groupId = "update-billing-account")
    private void consume(BillingAccountUpdatedEvent billingAccountUpdatedEvent){
        Customer customer = filterService.getById(billingAccountUpdatedEvent.getId());
        customer.setId(billingAccountUpdatedEvent.getId());
        customer.setAccountNumber(billingAccountUpdatedEvent.getAccountNumber());

        LOGGER.info(String.format("Billing Account updated event consumer => %s", billingAccountUpdatedEvent.toString()));
        this.filterService.updateCustomer(customer);
    }
}
