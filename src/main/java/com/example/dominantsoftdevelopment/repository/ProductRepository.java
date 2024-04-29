package com.example.dominantsoftdevelopment.repository;

import com.example.dominantsoftdevelopment.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByDeletedFalse();

    Optional<Product> findByIdAndDeletedFalse(Long id);

    List<Product> findBySellerIdAndDeletedFalse(Long seller_id);

    List<Product> findBySellerIdAndDeletedTrue(Long seller_id);

    @Query(nativeQuery = true, value = """
                 select year,count(*) from
                 (select extract(year from p.updated_at) as year from product p) as ss
                 group by ss.year
                    """)
    List<Product> getProductYear();
}
