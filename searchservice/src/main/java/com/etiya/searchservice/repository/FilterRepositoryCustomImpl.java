package com.etiya.searchservice.repository;

import com.etiya.searchservice.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import java.util.List;

public class FilterRepositoryCustomImpl implements FilterRepositoryCustom{
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Customer> searchResult
            (String nationalityIdentity, String id, String mobilePhone, String accountNumber, String firstName, String lastName, String orderNumber) {
        Query query = new Query();
        query.addCriteria(
                (nationalityIdentity != null) ? Criteria.where("nationalityIdentity").is(nationalityIdentity) :
                        (id != null) ? Criteria.where("id").is(id) :
                                (mobilePhone != null) ? Criteria.where("mobilePhone").is(mobilePhone) :
                                        (accountNumber != null) ? Criteria.where("accountNumber").is(accountNumber) :
                                                (firstName != null) ? Criteria.where("firstName").is(firstName) :
                                                        (lastName != null) ? Criteria.where("lastName").is(lastName) :
                                                                (orderNumber != null) ? Criteria.where("orderNumber").is(orderNumber) :
                                                                        null
        );
        return mongoTemplate.find(query, Customer.class);
    }
}