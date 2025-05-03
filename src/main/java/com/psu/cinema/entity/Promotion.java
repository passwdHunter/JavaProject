package com.psu.cinema.entity;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Promotion {
    private Long id;
    private String name;
    private String description;
    private String startDate; // Дата начала
    private String endDate; // Дата окончания
    private double discountValue; // Размер скидки
    private String conditions; // Условия применения

    // Конструкторы, геттеры и сеттеры
}
