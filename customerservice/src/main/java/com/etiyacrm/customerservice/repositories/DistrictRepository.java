package com.etiyacrm.customerservice.repositories;

import com.etiyacrm.customerservice.entities.District;
import com.etiyacrm.customerservice.entities.IndividualCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DistrictRepository extends JpaRepository<District, String> {

}
