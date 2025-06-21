package com.psu.cinema.service;

import com.psu.cinema.entity.Notification;
import com.psu.cinema.repository.NotificationRepository;
import com.psu.cinema.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository, UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public Optional<Notification> getNotificationById(Long id) {
        return notificationRepository.findById(id);
    }

    public Optional<Notification> createNotification(Notification notification) {
        if (notification.getUser() != null && !userRepository.existsById(notification.getUser().getId())) {
            return Optional.empty();
        }
        return Optional.of(notificationRepository.save(notification));
    }

    public Optional<Notification> updateNotification(Long id, Notification updatedNotification) {
        if (!notificationRepository.existsById(id)) {
            return Optional.empty();
        }
        if (updatedNotification.getUser() != null && !userRepository.existsById(updatedNotification.getUser().getId())) {
            return Optional.empty();
        }
        updatedNotification.setId(id);
        return Optional.of(notificationRepository.save(updatedNotification));
    }

    public boolean deleteNotification(Long id) {
        if (notificationRepository.existsById(id)) {
            notificationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}