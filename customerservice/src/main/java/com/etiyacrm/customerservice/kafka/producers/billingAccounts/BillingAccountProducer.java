package com.etiyacrm.customerservice.kafka.producers.billingAccounts;

import com.etiya.common.events.billingAccounts.BillingAccountCreatedEvent;
import com.etiya.common.events.billingAccounts.BillingAccountUpdatedEvent;
import com.etiyacrm.customerservice.kafka.producers.customers.CustomerProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class BillingAccountProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerProducer.class);
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public BillingAccountProducer (KafkaTemplate<String, Object> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(BillingAccountCreatedEvent billingAccountCreatedEvent){
        LOGGER.info(String.format("Billing Account created => %s", billingAccountCreatedEvent.toString()));

        Message<BillingAccountCreatedEvent> message = MessageBuilder.withPayload(billingAccountCreatedEvent)
                .setHeader(KafkaHeaders.TOPIC, "billing-account-created")
                .build();
        kafkaTemplate.send(message);
    }

    public void sendMessage(BillingAccountUpdatedEvent billingAccountUpdatedEvent){
        LOGGER.info(String.format("Billing Account updated => %s", billingAccountUpdatedEvent.toString()));

        Message<BillingAccountUpdatedEvent> message = MessageBuilder.withPayload(billingAccountUpdatedEvent)
                .setHeader(KafkaHeaders.TOPIC, "billing-account-update")
                .build();
        kafkaTemplate.send(message);
    }
}
