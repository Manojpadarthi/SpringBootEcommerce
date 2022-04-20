package com.luv2code.ecommerce.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.ecommerce.entity.Product;

@CrossOrigin("http://localhost:4200")
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	//get product 
	Page<Product> findByCategoryId(@RequestParam("id")Long id,Pageable pageable);
	
	//select name from product where name like '%sql%';
	//http://localhost:8083/api/products/search/findByNameContaining?name=sql
	Page<Product> findByNameContaining(@RequestParam("keyword")String keyword,Pageable pageable);
	
	

}
