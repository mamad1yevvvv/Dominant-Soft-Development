package com.example.dominantsoftdevelopment.model;

import com.example.dominantsoftdevelopment.model.baseData.BaseModel;
import com.example.dominantsoftdevelopment.model.enums.ConditionProduct;
import com.example.dominantsoftdevelopment.model.enums.PayType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Product extends BaseModel {

    @Column(nullable = false)
    String productName;

    @Column(nullable = false)
    Double price;

    @ManyToOne
    Category productCategory;

    Boolean availability;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    PayType payType;


    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    ConditionProduct conditionProduct;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    User seller;

    @OneToMany
    List<Attachment> attachment;
}
