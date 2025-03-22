package entity;

import java.util.List;

public class Hall {
    private Long id;
    private String name;
    private int capacity;
    private String type; // Обычный, VIP, IMAX
    private String seatingPlan; // Схема рассадки
    private List<Session> sessions; // Сеансы

    // Конструкторы, геттеры и сеттеры
}
