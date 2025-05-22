package com.example.Backend.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Backend.Entity.ProductEntity;
import com.example.Backend.Services.ProductImportService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class ProductController {
	 @Autowired
	    private ProductImportService productImportService;

	    @PostMapping("/import-fake")
	    public ResponseEntity<String> importFakeProducts() {
	        productImportService.fetchAndSaveProducts();
	        return ResponseEntity.ok("Products imported successfully");
	    }
 
	    @GetMapping("/getproducts")
	    public List<ProductEntity> getproducts(){
	    	return productImportService.getallproducts();
	    }

	    @GetMapping("/getproduct/{id}")
	    public Optional<ProductEntity> getproduct(@PathVariable String id){
	    	return productImportService.getproductById(id);
	    }
	    
	    @PostMapping("/newproduct")
	    public ProductEntity insert(@RequestBody ProductEntity insert) {
	    	return productImportService.insertproduct(insert);
	    }
	    
	    @DeleteMapping("/removeproduct/{id}")
	    public void remove(@PathVariable String id) {
	    	productImportService.deleteproduct(id);
	    }
}
