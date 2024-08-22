package com.monkey.monkey.Service;

import javax.persistence.EntityManager;

import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monkey.monkey.GlobelException.MyException;
import com.monkey.monkey.Repository.ProductReposoitory;
import com.monkey.monkey.models.Product;

@Service
public class ProductService {
	@Autowired
	private EntityManager entityManager;

	@Autowired
	private ProductReposoitory productReposoitory;
	
	public void  addProduct(Product product) {
		productReposoitory.save(product);
	}
	
	public Product getProductById(Long productId) {
		return productReposoitory.getProductByid(productId,false);
	}

	public Iterable<Product> findAllProduct(boolean isDeleted) {
	    Session session = entityManager.unwrap(Session.class);
	    Filter filter = session.enableFilter("deletedProductFilter");
	    filter.setParameter("isDeleted", isDeleted);
	    Iterable<Product> appUserEntities = productReposoitory.findAll();
	    session.disableFilter("deletedProductFilter");
	    return appUserEntities;
	}
	
	public void deleteProductById(Long id) {
	    try {
	    	Product appUser = productReposoitory.getProductByid(id, false);
	    	if(appUser==null) {
	    		throw new MyException("Product not present to delete");
	    	}
	    	productReposoitory.deleteById(id);
	      } catch (Exception e) {
	          System.err.println("An error occurred while deleting the product: " + e.getMessage());
	      }
	  }
}
