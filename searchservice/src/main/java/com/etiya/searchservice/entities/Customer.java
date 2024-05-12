package com.etiya.searchservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "search-customer")
public class Customer {
    @Id
    private String id;

    @Field(name = "firstName")
    private String firstName;

    @Field(name = "middleName")
    private String middleName;

    @Field(name = "lastName")
    private String lastName;

    @Field(name = "gender")
    private String gender;

    @Field(name = "birthDate")
    private Date birthDate;

    @Field(name = "fatherName")
    private String fatherName;

    @Field(name = "motherName")
    private String motherName;

    @Field(name = "nationalityIdentity")
    private String nationalityIdentity;
}
