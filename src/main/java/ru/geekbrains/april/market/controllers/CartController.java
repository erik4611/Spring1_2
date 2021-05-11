package ru.geekbrains.april.market.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.april.market.dtos.ProductDto;
import ru.geekbrains.april.market.models.Product;
import ru.geekbrains.april.market.services.ProductService;
import ru.geekbrains.april.market.utils.Cart;
import ru.geekbrains.april.market.dto.CartDto;
import java.util.List;


@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@Slf4j
public class CartController {
    private final Cart cart;
    //    private final CartDto cartDto;
    private final ProductService productService;


    @GetMapping()
    public List<ProductDto> getCartDto() {
        CartDto cartDto=new CartDto(cart);
        return cartDto.getCartDtoItems();
    }

    @GetMapping("/add")
    public int add(@RequestParam Long id) {
        log.info("add to cart product with id=" + id);
        return cart.addItem(productService.findProductByID(id).get());
    }

    @GetMapping("/delete")
    public int deleteProductFromCart(@RequestParam Long id) {
        log.info("delete from cart product with id=" + id);
        return cart.deleteItem(id);
    }

    @GetMapping("/summ")
    public int summCart() {
        return cart.sumItems();
    }
}