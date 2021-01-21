package com.jmrbdev.primerrest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;

import com.jmrbdev.primerrest.repos.ProductRepository;
import com.jmrbdev.primerrest.entities.Product;

@RestController
public class ProductRestController {
	@Autowired
	ProductRepository repository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductRestController.class);

	/**
	 * Método GET
	 */
	@RequestMapping(value = "/products/", method = RequestMethod.GET)
	public List<Product> getProducts() {
		return repository.findAll();
	}

	/**
	 * Método GET (producto por su ID)
	 */
	@RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
	public Product getProduct(@PathVariable("id") int id) {
		LOGGER.info("Finding product by ID:"+id);
		return repository.findById(id).get();
	}

	/**
	 * Método POST
	 */
	@RequestMapping(value = "/products/", method = RequestMethod.POST)
	public Product createProduct(@RequestBody Product product) {
		return repository.save(product);
	}

	/**
	 * Método PUT
	 */
	@RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
	public Product updateProduct(@PathVariable("id") int id, @RequestBody Product product) {
		Product producto = repository.findById(id).get();
		producto.setName(product.getName());
		producto.setDescription(product.getDescription());
		producto.setPrice(product.getPrice());
		return repository.save(producto);
	}
	
	/**
	 * Método PATCH
	 */
	@RequestMapping(value = "/products/{id}", method = RequestMethod.PATCH)
	public Product updateField(@PathVariable("id") int id, @RequestBody Product product) {
		Product oldProduct = repository.findById(id).get();
		
		product.setId(oldProduct.getId());
		
		if (product.getName() == null) {
			product.setName(oldProduct.getName());
		}
		
		if (product.getDescription() == null) {
			product.setDescription(oldProduct.getDescription());
		}
		
		if (product.getPrice() == null) {
			product.setPrice(oldProduct.getPrice());
		}
		
		return repository.save(product);
	}

	/**
	 * Método DELETE
	 */
	@RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
	public void deleteProduct(@PathVariable("id") int id) {
		repository.deleteById(id);
	}
}
