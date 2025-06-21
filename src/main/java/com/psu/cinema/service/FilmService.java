package com.psu.cinema.service;

import com.psu.cinema.entity.Film;
import com.psu.cinema.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmService {

    private final FilmRepository filmRepository;

    @Autowired
    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    public Optional<Film> getFilmById(Long id) {
        return filmRepository.findById(id);
    }

    public Film createFilm(Film film) {
        return filmRepository.save(film);
    }

    public Optional<Film> updateFilm(Long id, Film updatedFilm) {
        return filmRepository.findById(id)
                .map(existingFilm -> {
                    existingFilm.setTitle(updatedFilm.getTitle());
                    existingFilm.setGenre(updatedFilm.getGenre());
                    existingFilm.setDurationMinutes(updatedFilm.getDurationMinutes());
                    existingFilm.setReleaseDate(updatedFilm.getReleaseDate());
                    return filmRepository.save(existingFilm);
                });
    }

    public boolean deleteFilm(Long id) {
        if (filmRepository.existsById(id)) {
            filmRepository.deleteById(id);
            return true;
        }
        return false;
    }
}