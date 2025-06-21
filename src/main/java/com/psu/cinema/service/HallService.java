package com.psu.cinema.service;

import com.psu.cinema.entity.Hall;
import com.psu.cinema.repository.CinemaRepository;
import com.psu.cinema.repository.HallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HallService {

    private final HallRepository hallRepository;
    private final CinemaRepository cinemaRepository;

    @Autowired
    public HallService(HallRepository hallRepository, CinemaRepository cinemaRepository) {
        this.hallRepository = hallRepository;
        this.cinemaRepository = cinemaRepository;
    }

    public List<Hall> getAllHalls() {
        return hallRepository.findAll();
    }

    public Optional<Hall> getHallById(Long id) {
        return hallRepository.findById(id);
    }

    public Optional<Hall> createHall(Hall hall) {
        // Проверяем, существует ли связанный кинотеатр
        if (hall.getCinema() == null || !cinemaRepository.existsById(hall.getCinema().getId())) {
            return Optional.empty();
        }
        return Optional.of(hallRepository.save(hall));
    }

    public Optional<Hall> updateHall(Long id, Hall updatedHall) {
        // Проверяем, существует ли зал
        if (!hallRepository.existsById(id)) {
            return Optional.empty();
        }
        // Проверяем, существует ли связанный кинотеатр
        if (updatedHall.getCinema() == null || !cinemaRepository.existsById(updatedHall.getCinema().getId())) {
            return Optional.empty();
        }
        updatedHall.setId(id); // Устанавливаем ID для обновления
        return Optional.of(hallRepository.save(updatedHall));
    }

    public boolean deleteHall(Long id) {
        if (hallRepository.existsById(id)) {
            hallRepository.deleteById(id);
            return true;
        }
        return false;
    }
}