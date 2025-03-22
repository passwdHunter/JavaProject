package entity;

import java.util.List;

public class Film {
    private Long id;
    private String title;
    private String description;
    private int duration; // В минутах
    private String releaseDate;
    private String cast;
    private String director;
    private String genre;
    private String ageRestrictions;
    private String posterUrl;
    private String trailerUrl;
    private double rating;
    private List<String> formats; // 2D, 3D, IMAX
    private List<Review> reviews; // Отзывы

    // Конструкторы, геттеры и сеттеры
}
