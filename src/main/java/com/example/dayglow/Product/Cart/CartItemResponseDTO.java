package com.example.dayglow.Product.Cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartItemResponseDTO {

    private Long cartItemId;
    private String name;
    private Integer InsertQuantity;
    private Integer price;

    public static CartItemResponseDTO toDTO (CartItem cartItem, String name, int price) {
        return new CartItemResponseDTO(cartItem.getId(), name, cartItem.getQuantity(), price);
    }
}
