package com.example.demo.todo.dto;

import com.example.demo.todo.models.Cart;
import com.example.demo.todo.models.CartDetails;
import com.example.demo.todo.models.Product;
import com.example.demo.todo.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDto {

    private String id;
    private List<String> cartDetailsIds;
    private String userId;


    public static CartDto from(Cart cart) {

        List<String> cartDetailsIds = new ArrayList<>();

        if (cartDetailsIds != null) {

            cartDetailsIds = cart.getCartDetails().stream().map(CartDetails::getId).collect(Collectors.toList());
        }

        return CartDto.builder()
                .cartDetailsIds(cartDetailsIds)
                .userId(cart.getUser().getId())
                .build();
    }

    public static List<CartDto> from (List<Cart> carts){

       return carts.stream().map(CartDto::from).collect(Collectors.toList());
    }

}



