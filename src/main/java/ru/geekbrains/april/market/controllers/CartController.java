package ru.geekbrains.april.market.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.april.market.models.Product;
import ru.geekbrains.april.market.utils.Cart;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@Slf4j
public class CartController {
    private final Cart cart;

    @GetMapping("/ping")
    public void ping(@RequestParam Long id) {
        log.info("ping: " + id);
    }

    @PostMapping("/add/{id}")
    public void addProductInCart(@RequestBody Long id, String title, int price) {
        Product product = new Product(id, title, price);
        cart.addProduct(id);
    }
    @DeleteMapping
    public void deleteAllCart() {
        cart.clearCart();
    }
}
