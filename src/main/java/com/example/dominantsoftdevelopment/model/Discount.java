package com.example.dominantsoftdevelopment.model;

import com.example.dominantsoftdevelopment.model.baseData.BaseModel;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Discount extends BaseModel {

    @Column(nullable = false)
    int percentage;

    @ManyToOne
    Product productId;

    Boolean isActive;

    @Column(nullable = false)
    LocalDateTime finishedAt;
}
