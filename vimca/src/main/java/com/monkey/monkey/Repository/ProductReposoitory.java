package com.monkey.monkey.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.monkey.monkey.models.Product;

public interface ProductReposoitory  extends JpaRepository<Product, Long>{

	@Query("select d from Product d where d.productId=:productId AND d.deleted=:isDeleted")
	Product getProductByid(Long productId, boolean isDeleted);

}
