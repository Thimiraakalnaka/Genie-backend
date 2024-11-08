package com.example.genie.controller;

import com.example.genie.dto.ProductDTO;
import com.example.genie.service.ProductService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

    @PostMapping("/addproduct")
    public ProductDTO saveProduct(@RequestBody ProductDTO productDTO){
        return productService.saveProducts(productDTO);
    }

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

//    @PostMapping("/addproduct")
//    public ResponseEntity<?> addProduct(@RequestPart ProductDTO productDTO,
//                                        @RequestPart MultipartFile imageFile){
//
//        try{
//            ProductDTO productDTO1= productService.addProduct(productDTO, imageFile);
//            return new ResponseEntity<>(productDTO1, HttpStatus.CREATED);
//        }catch (Exception e){
//            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }


}
