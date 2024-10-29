package com.example.genie.controller;

import com.example.genie.dto.ProductDTO;
import com.example.genie.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
