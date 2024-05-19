package com.etiya.common.events.billingAccounts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillingAccountUpdatedEvent {
    private String id;
    private String accountNumber;
}
