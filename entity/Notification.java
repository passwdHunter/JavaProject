package entity;

public class Notification {
    private Long id;
    private User user; // Для кого уведомление
    private String message; // Текст уведомления
    private String type; // Тип (скидка, сеанс, изменение расписания)
    private String sendTime; // Время отправки
    private boolean isRead; // Прочитано или нет

    // Конструкторы, геттеры и сеттеры
}
