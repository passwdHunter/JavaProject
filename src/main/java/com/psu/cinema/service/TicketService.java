package com.psu.cinema.service;

import com.psu.cinema.entity.Ticket;
import com.psu.cinema.repository.SessionRepository;
import com.psu.cinema.repository.TicketRepository;
import com.psu.cinema.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository, SessionRepository sessionRepository, UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Optional<Ticket> getTicketById(Long id) {
        return ticketRepository.findById(id);
    }

    public Optional<Ticket> createTicket(Ticket ticket) {
        if (ticket.getSession() == null || !sessionRepository.existsById(ticket.getSession().getId())) {
            return Optional.empty();
        }
        if (ticket.getUser() != null && !userRepository.existsById(ticket.getUser().getId())) {
            return Optional.empty();
        }
        return Optional.of(ticketRepository.save(ticket));
    }

    public Optional<Ticket> updateTicket(Long id, Ticket updatedTicket) {
        if (!ticketRepository.existsById(id)) {
            return Optional.empty();
        }
        if (updatedTicket.getSession() == null || !sessionRepository.existsById(updatedTicket.getSession().getId())) {
            return Optional.empty();
        }
        if (updatedTicket.getUser() != null && !userRepository.existsById(updatedTicket.getUser().getId())) {
            return Optional.empty();
        }
        updatedTicket.setId(id);
        return Optional.of(ticketRepository.save(updatedTicket));
    }

    public boolean deleteTicket(Long id) {
        if (ticketRepository.existsById(id)) {
            ticketRepository.deleteById(id);
            return true;
        }
        return false;
    }
}