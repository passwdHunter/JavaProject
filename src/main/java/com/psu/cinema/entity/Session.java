package com.psu.cinema.entity;

import lombok.*;

import java.util.List;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Session {
    private Long id;
    private Film film; // Связь с фильмом
    private Hall hall; // Связь с залом
    private String dateTime; // Дата и время сеанса
    private String format; // 2D, 3D, IMAX
    private double price;
    private List<String> availableSeats; // Доступные места

    // Конструкторы, геттеры и сеттеры
}
