package com.example.dayglow.Product.Cart;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartCreateRequestDTO {
    @NotNull(message = "상품 번호를 입력하세요.")
    private Long product_id;

    @NotNull(message = "상품 수량을 입력하세요.")
    private Integer quantity;
}
