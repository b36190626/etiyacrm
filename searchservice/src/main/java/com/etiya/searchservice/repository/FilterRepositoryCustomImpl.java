package com.etiya.searchservice.repository;

import com.etiya.searchservice.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.List;

public class FilterRepositoryCustomImpl implements FilterRepositoryCustom{
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Customer> searchResult
            (String nationalityIdentity, String id, String mobilePhone, String accountNumber, String firstName, String lastName, String orderNumber) {

        List<Criteria> criteriaList = new ArrayList<>();

        criteriaList.add(nationalityIdentity != null && !nationalityIdentity.isEmpty() ? Criteria.where("nationalityIdentity").regex(nationalityIdentity, "i") : null);
        criteriaList.add(id != null && !id.isEmpty() ? Criteria.where("id").regex(id, "i") : null);
        criteriaList.add(mobilePhone != null && !mobilePhone.isEmpty() ? Criteria.where("mobilePhone").regex(mobilePhone, "i") : null);
        criteriaList.add(accountNumber != null && !accountNumber.isEmpty() ? Criteria.where("accountNumber").regex(accountNumber, "i") : null);
        criteriaList.add(firstName != null && !firstName.isEmpty() ? Criteria.where("firstName").regex(firstName, "i") : null);
        criteriaList.add(lastName != null && !lastName.isEmpty() ? Criteria.where("lastName").regex(lastName, "i") : null);
        criteriaList.add(orderNumber != null && !orderNumber.isEmpty() ? Criteria.where("orderNumber").regex(orderNumber, "i") : null);

        criteriaList.removeIf(criteria -> criteria == null);

        if (criteriaList.isEmpty()) {
            return mongoTemplate.findAll(Customer.class);
        }

        Query query = new Query();
        query.addCriteria(new Criteria().andOperator(criteriaList.toArray(new Criteria[0])));

        return mongoTemplate.find(query, Customer.class);
    }
}