package com.psu.cinema.entity;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Ticket {
    private Long id;
    private User user; // Кто купил билет
    private Session session; // На какой сеанс
    private String seatNumber; // Номер кресла
    private double price;
    private String qrCode; // QR-код для входа
    private String status; // Куплен, забронирован, отменён

    // Конструкторы, геттеры и сеттеры
}
