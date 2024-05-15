package com.etiyacrm.customerservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="billing_accounts")
@Data
@PrimaryKeyJoinColumn(name = "account_id")
public class BillingAccount extends Account{

}
