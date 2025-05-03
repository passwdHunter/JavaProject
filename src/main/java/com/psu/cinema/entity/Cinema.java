package com.psu.cinema.entity;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cinema {
    private Long id;
    private String name;
    private String address;
    private int hallCount;
    @ToString.Exclude
    private List<Hall> halls; // Список залов
    private String contactInfo;

    // Конструкторы, геттеры и сеттеры
}
