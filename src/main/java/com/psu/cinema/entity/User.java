package com.psu.cinema.entity;

import lombok.*;

import java.util.List;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String role; // Администратор или пользователь
    private List<Session> visitHistory; // История посещений
    private List<Film> favoriteFilms; // Избранные фильмы
    private List<Notification> notifications; // Уведомления

    // Конструкторы, геттеры и сеттеры
}
