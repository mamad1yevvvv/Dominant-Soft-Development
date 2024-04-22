package com.example.dominantsoftdevelopment.model.baseData;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class  BaseModel extends AbsAuditingEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
}
