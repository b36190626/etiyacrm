package com.etiya.common.events.billingAccounts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillingAccountCreatedEvent {
    private String id;
    private String accountNumber;
}
