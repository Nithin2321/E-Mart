package com.example.Backend.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.Backend.Entity.FakeProduct;
import com.example.Backend.Entity.ProductEntity;
import com.example.Backend.Repo.ProductRepository;

@Service
public class ProductImportService {
	  @Autowired
	    private RestTemplate restTemplate;

	    @Autowired
	    private ProductRepository productRepository;

	    public void fetchAndSaveProducts() {
	        String url = "https://fakestoreapi.com/products";
	        ResponseEntity<FakeProduct[]> response = restTemplate.getForEntity(url, FakeProduct[].class);

	        FakeProduct[] fakeProducts = response.getBody();
	        if (fakeProducts != null) {
	            for (FakeProduct fp : fakeProducts) {
	                ProductEntity p = new ProductEntity();
	                p.setName(fp.getTitle());
	                p.setDescription(fp.getDescription());
	                p.setCategory(fp.getCategory());
	                p.setPrice(fp.getPrice());
	                p.setImageUrl(fp.getImage());
	                productRepository.save(p);
	            }
	        }
	    }
	    
	    public List<ProductEntity> getallproducts(){
	    	return productRepository.findAll();
	    }
	    
	    public Optional<ProductEntity> getproductById(String id) {
	    	return productRepository.findById(id);
	    	
	    }
	    
	    public ProductEntity insertproduct(ProductEntity newproduct){
	    	return productRepository.save(newproduct);
	    }
	    
	    public void deleteproduct(String id) {
	    	productRepository.deleteById(id);
	    }

	    
}
