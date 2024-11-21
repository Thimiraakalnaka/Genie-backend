package com.example.genie.controller;

import com.example.genie.dto.ProductDTO;
import com.example.genie.model.Product;
import com.example.genie.service.ProductService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/getproduct")
    public List<ProductDTO> getProduct(){

        return productService.getAllProducts();
    }

    @GetMapping("/getproduct/{productId}")
    public ProductDTO getProductById(@PathVariable Integer productId){
        return productService.getProductById(productId);
    }

//    @PostMapping("/addproduct")
//    public ProductDTO saveProduct(@RequestBody ProductDTO productDTO){
//        return productService.saveProducts(productDTO);
//    }

    @PutMapping("/updateproduct")
    public ProductDTO updateProduct(@RequestBody ProductDTO productDTO){
        return productService.updateProduct(productDTO);
    }

    @DeleteMapping("/deleteproduct")
    public String deleteProduct(@RequestBody ProductDTO productDTO){
        return productService.deleteProduct(productDTO);
    }

    @DeleteMapping("/deleteproduct/{productId}")
    public String deleteProductById(@PathVariable Integer productId){
        return productService.deleteProductById(productId);
    }

    @PostMapping("/addproduct")
    public ResponseEntity<?> addProduct(@RequestBody ProductDTO productDTO) {
        try {
            // Pass the ProductDTO to a service that handles saving
            productService.saveProduct(productDTO);
            return ResponseEntity.ok("Product added successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId){

        ProductDTO product = productService.getProductById(productId);
        byte[] imageFile = product.getImageDate();

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(product.getImageType()))
                .body(imageFile);
    }




}
