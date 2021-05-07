package ru.geekbrains.april.market.utils;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class Cart {
    private List<Long> items;

    @PostConstruct
    public void init() {
        items = new ArrayList<>();
    }

    public void addProduct(Long product) {
        items.add(product);
    }

    public void clearCart() {
        items.clear();
    }


}
