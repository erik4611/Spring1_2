package ru.geekbrains.april.market.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import ru.geekbrains.april.market.dtos.ProductDto;
import ru.geekbrains.april.market.models.Product;
import ru.geekbrains.april.market.utils.Cart;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Data
@NoArgsConstructor
public class CartDto {

    private List<ProductDto> cartDtoItems;

    public CartDto(Cart cart) {
        this.cartDtoItems = cart.getItems().stream().map(ProductDto::new).collect(Collectors.toList());
    }
    @PostConstruct
    public void init() {
        cartDtoItems = new ArrayList<>();
    }

}
