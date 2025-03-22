package entity;

public class Payment {
    private Long id;
    private User user; // Кто оплатил
    private Ticket ticket; // За какой билет
    private double amount; // Сумма
    private String paymentMethod; // Способ оплаты
    private String status; // Успешный, отменённый
    private String paymentDateTime; // Дата и время платежа

    // Конструкторы, геттеры и сеттеры
}
