package entity;

import java.util.List;

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
