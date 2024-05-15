package com.etiyacrm.customerservice.repositories;

import com.etiyacrm.customerservice.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccpuntRepository extends JpaRepository<Account, String> {
}
