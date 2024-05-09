package com.etiyacrm.customerservice.entities;

import com.etiyacrm.customerservice.core.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "districts")
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class District extends BaseEntity {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name="name")
    private String name;

    @ManyToOne()
    @JoinColumn(name = "city_id")
    private City city;
}
