package entity;

public class Review {
    private Long id;
    private User user; // Кто оставил отзыв
    private Film film; // На какой фильм
    private String content; // Текст отзыва
    private int rating; // Рейтинг (1-5)
    private String dateTime; // Дата и время публикации

    // Конструкторы, геттеры и сеттеры
}
