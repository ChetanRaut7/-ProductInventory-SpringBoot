package com.Sathya.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Sathya.mvc.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>
	{
	
	
}
