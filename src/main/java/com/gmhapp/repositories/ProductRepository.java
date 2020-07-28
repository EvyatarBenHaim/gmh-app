package com.gmhapp.repositories;

import com.gmhapp.entities.ProductEntity;
import com.gmhapp.enums.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    @Query("SELECT p FROM ProductEntity p WHERE p.name = :name")
    List<ProductEntity> findAllByName(@Param("name") String name);

    @Query("SELECT p FROM ProductEntity p WHERE p.category = :category")
    List<ProductEntity> findAllByCategory(@Param("category") ProductCategory category);

}
