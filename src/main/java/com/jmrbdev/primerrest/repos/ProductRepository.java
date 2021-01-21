package com.jmrbdev.primerrest.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jmrbdev.primerrest.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
