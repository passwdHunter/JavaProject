package com.psu.cinema.controller;

import com.psu.cinema.entity.Notification;
import com.psu.cinema.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@Tag(name = "Notification API", description = "API for managing notifications in the system")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Operation(summary = "Get all notifications", description = "Retrieves a list of all notifications")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of notifications")
    })
    @GetMapping
    public List<Notification> getAllNotifications() {
        return notificationService.getAllNotifications();
    }

    @Operation(summary = "Get notification by ID", description = "Retrieves a notification by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved notification"),
            @ApiResponse(responseCode = "404", description = "Notification not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Notification> getNotificationById(
            @Parameter(description = "ID of the notification to retrieve") @PathVariable Long id) {
        return notificationService.getNotificationById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new notification", description = "Creates a new notification in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created notification"),
            @ApiResponse(responseCode = "400", description = "Invalid notification data or user not found")
    })
    @PostMapping
    public ResponseEntity<Notification> createNotification(
            @Parameter(description = "Notification data to create") @Valid @RequestBody Notification notification) {
        return notificationService.createNotification(notification)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Update a notification", description = "Updates an existing notification by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated notification"),
            @ApiResponse(responseCode = "404", description = "Notification not found"),
            @ApiResponse(responseCode = "400", description = "Invalid notification data or user not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Notification> updateNotification(
            @Parameter(description = "ID of the notification to update") @PathVariable Long id,
            @Parameter(description = "Updated notification data") @Valid @RequestBody Notification notification) {
        return notificationService.updateNotification(id, notification)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a notification", description = "Deletes a notification by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted notification"),
            @ApiResponse(responseCode = "404", description = "Notification not found")
    })@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(
            @Parameter(description = "ID of the notification to delete") @PathVariable Long id) {
        if (notificationService.deleteNotification(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}