package ru.geekbrains.april.market.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.april.market.models.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@Component
@RequiredArgsConstructor
public class Cart {
    public List<Product> getItems() {
        return items;
    }
    public int addItem(Product item) {
        this.items.add(item);
        return sumItems();
    }
    public int deleteItem(Long item) {
        for (Product product : items) {
            if (product.getId().equals(item)) {
                items.remove(product);
                break;
            }
        }
        return sumItems();
    }
    public int sumItems() {
        int result = 0;
        for (Product product : items) {
            result += product.getPrice();
        }
        return result;
    }
    private List<Product> items;
    @PostConstruct
    public void init() {
        items = new ArrayList<>();
    }
}
