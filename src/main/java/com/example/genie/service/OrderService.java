package com.example.genie.service;

import com.example.genie.dto.OrdersDTO;
import com.example.genie.model.Orders;
import com.example.genie.model.Product;
import com.example.genie.model.User;
import com.example.genie.repo.OrderRepo;
import com.example.genie.repo.ProductRepo;
import com.example.genie.repo.UserRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<OrdersDTO> getAllOrders(){
        List<Orders> ordersList = orderRepo.findAll();

        return modelMapper.map(ordersList,new TypeToken<List<OrdersDTO>>(){}.getType());
    }

//    public OrdersDTO saveOrder(OrdersDTO ordersDTO){
//        orderRepo.save(modelMapper.map(ordersDTO,Orders.class));
//        return ordersDTO;
//    }

    public OrdersDTO saveOrder(OrdersDTO ordersDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userRepo.findByEmail(username);
        if (user == null) {
            throw new RuntimeException("User not found!");
        }


        Product product = productRepo.findById(ordersDTO.getProductid())
                .orElseThrow(() -> new RuntimeException("Product not found!"));

        if (product.getQuantity() < ordersDTO.getQuantity()) {
            throw new RuntimeException("Not enough stock available!");
        }

        product.setQuantity(product.getQuantity() - ordersDTO.getQuantity());
        productRepo.save(product);

        Orders order = new Orders();
        order.setUser(user);
        order.setProduct(product);
        order.setQuantity(ordersDTO.getQuantity());
        order.setPrice(product.getPrice() * ordersDTO.getQuantity());

        Orders savedOrder = orderRepo.save(order);

        OrdersDTO responseDTO = modelMapper.map(savedOrder, OrdersDTO.class);
        responseDTO.setId(user.getId());
        return responseDTO;
    }

}
