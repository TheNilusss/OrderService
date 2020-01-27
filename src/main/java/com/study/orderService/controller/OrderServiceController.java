package com.study.orderService.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.orderService.entity.Cart;
import com.study.orderService.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.study.orderService.repository.OrderRepository;

@RestController
public class OrderServiceController {

    @Autowired
    private OrderRepository m_repository;

    @GetMapping("/showDb")
    String showDb()
    {
        String l_string = "";
        for (Order customer : m_repository.findAll()) {
            l_string += customer.toString();
        }
        return l_string;
    }

    @PostMapping("/order")
    void order(@RequestParam final String CN)
    {
        System.out.println("[INFO] Receive Order Call");

        RestTemplate restTemplate = new RestTemplate();

        String fooResourceUrl = "http://localhost:8083/getCart?CN=" + CN;
        ResponseEntity<Cart> response = restTemplate.getForEntity(fooResourceUrl, Cart.class);
        //Cart l_cart = response.getBody();
        System.out.println(response.getBody().getCID());
    }
}
