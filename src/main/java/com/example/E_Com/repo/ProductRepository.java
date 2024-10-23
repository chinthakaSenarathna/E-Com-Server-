package com.example.E_com.repo;

import com.example.E_com.dto.response.paginate.ProductPaginateDto;
import com.example.E_com.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface ProductRepository extends JpaRepository<Product, String> {

    @Query(
            value = "SELECT * FROM product WHERE description LIKE %?1% ORDER BY property_id DESC",
            nativeQuery = true
    )
    public Page<Product> findAllWithSearchText(String searchText, Pageable pageable);

    @Query(
            value = "SELECT COUNT(*) FROM product WHERE description LIKE %?1%",
            nativeQuery = true
    )
    public long countAllWithSearchText(String searchText);

}
