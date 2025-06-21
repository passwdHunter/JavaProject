package com.psu.cinema.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ticket", schema = "public")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    private Session session;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @Min(1)
    @Column(name = "seat_number", nullable = false)
    private Integer seatNumber;

    @NotBlank
    @Pattern(regexp = "AVAILABLE|BOOKED|PURCHASED|INACTIVE")
    @Column(name = "status", nullable = false)
    private String status;

    @NotNull
    @DecimalMin("0.00")
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}