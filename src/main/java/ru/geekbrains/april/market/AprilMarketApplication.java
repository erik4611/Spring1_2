package ru.geekbrains.april.market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AprilMarketApplication {
    // Домашнее задание:
    // 1. Добавить возможно добавлять товары в корзину (надимая на кнопку Добавить в корзину)
    // 2. На странице под формой добавления нового товара необходимо
    // добавить таблицу с товарами, которые лежат в корзине
    // 3. Под таблицей с корзиной сделать кнопку "Очистить корзину"

    public static void main(String[] args) {
        SpringApplication.run(AprilMarketApplication.class, args);
    }
}
