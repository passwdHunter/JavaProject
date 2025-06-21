package com.psu.cinema.service;

import com.psu.cinema.entity.Session;
import com.psu.cinema.repository.FilmRepository;
import com.psu.cinema.repository.HallRepository;
import com.psu.cinema.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;
    private final FilmRepository filmRepository;
    private final HallRepository hallRepository;

    @Autowired
    public SessionService(SessionRepository sessionRepository, FilmRepository filmRepository, HallRepository hallRepository) {
        this.sessionRepository = sessionRepository;
        this.filmRepository = filmRepository;
        this.hallRepository = hallRepository;
    }

    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }

    public Optional<Session> getSessionById(Long id) {
        return sessionRepository.findById(id);
    }

    public Optional<Session> createSession(Session session) {
        if (session.getFilm() == null || !filmRepository.existsById(session.getFilm().getId())) {
            return Optional.empty();
        }
        if (session.getHall() == null || !hallRepository.existsById(session.getHall().getId())) {
            return Optional.empty();
        }
        return Optional.of(sessionRepository.save(session));
    }

    public Optional<Session> updateSession(Long id, Session updatedSession) {
        if (!sessionRepository.existsById(id)) {
            return Optional.empty();
        }
        if (updatedSession.getFilm() == null || !filmRepository.existsById(updatedSession.getFilm().getId())) {
            return Optional.empty();
        }
        if (updatedSession.getHall() == null || !hallRepository.existsById(updatedSession.getHall().getId())) {
            return Optional.empty();
        }
        updatedSession.setId(id);
        return Optional.of(sessionRepository.save(updatedSession));
    }

    public boolean deleteSession(Long id) {
        if (sessionRepository.existsById(id)) {
            sessionRepository.deleteById(id);
            return true;
        }
        return false;
    }
}