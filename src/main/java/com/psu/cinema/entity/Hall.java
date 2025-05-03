package com.psu.cinema.entity;

import lombok.*;

import java.util.List;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Hall {
    private Long id;
    private String name;
    private int capacity;
    private String type; // Обычный, VIP, IMAX
    private String seatingPlan; // Схема рассадки
    private List<Session> sessions; // Сеансы

    // Конструкторы, геттеры и сеттеры
}
