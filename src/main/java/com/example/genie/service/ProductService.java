package com.example.genie.service;

import com.example.genie.dto.ProductDTO;
import com.example.genie.model.Product;
import com.example.genie.repo.ProductRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<ProductDTO> getAllProducts(){
        List<Product> productList = productRepo.findAll();

        return modelMapper.map(productList, new TypeToken<List<ProductDTO>>(){}.getType());
    }

    public ProductDTO getProductById(Integer productId) {
        Product product = productRepo.getProductById(productId);
        return modelMapper.map(product,ProductDTO.class);
    }

    public ProductDTO saveProducts(ProductDTO productDTO){
        productRepo.save(modelMapper.map(productDTO, Product.class));
        return productDTO;
    }

    public ProductDTO updateProduct(ProductDTO productDTO){
        productRepo.save(modelMapper.map(productDTO, Product.class));
        return productDTO;
    }

    public String deleteProduct(ProductDTO productDTO){
        productRepo.delete(modelMapper.map(productDTO, Product.class));
        return "product deleted";
    }

    public String deleteProductById(Integer productId) {
        productRepo.deleteById(productId);
        return "product deleted";
    }


//    public ProductDTO addProduct(ProductDTO productDTO, MultipartFile imageFile) throws IOException {
//        productDTO.setImageName(imageFile.getOriginalFilename());
//        productDTO.setImageType(imageFile.getContentType());
//        productDTO.setImageDate(imageFile.getBytes());
//        productRepo.save(modelMapper.map(productDTO, Product.class));
//        return productDTO;
//
//    }
}
