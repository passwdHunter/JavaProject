package com.psu.cinema.service;

import com.psu.cinema.entity.Cinema;
import com.psu.cinema.repository.CinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CinemaService {

    private final CinemaRepository cinemaRepository;

    @Autowired
    public CinemaService(CinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }

    public List<Cinema> getAllCinemas() {
        return cinemaRepository.findAll();
    }

    public Optional<Cinema> getCinemaById(Long id) {
        return cinemaRepository.findById(id);
    }

    public Cinema createCinema(Cinema cinema) {
        return cinemaRepository.save(cinema);
    }

    public Optional<Cinema> updateCinema(Long id, Cinema updatedCinema) {
        return cinemaRepository.findById(id)
                .map(existingCinema -> {
                    existingCinema.setName(updatedCinema.getName());
                    existingCinema.setAddress(updatedCinema.getAddress());
                    existingCinema.setCity(updatedCinema.getCity());
                    return cinemaRepository.save(existingCinema);
                });
    }

    public boolean deleteCinema(Long id) {
        if (cinemaRepository.existsById(id)) {
            cinemaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}