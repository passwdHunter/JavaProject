package com.psu.cinema.service;

import com.psu.cinema.entity.Payment;
import com.psu.cinema.repository.PaymentRepository;
import com.psu.cinema.repository.TicketRepository;
import com.psu.cinema.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, TicketRepository ticketRepository, UserRepository userRepository) {
        this.paymentRepository = paymentRepository;
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Optional<Payment> getPaymentById(Long id) {
        return paymentRepository.findById(id);
    }

    public Optional<Payment> createPayment(Payment payment) {
        if (payment.getTicket() == null || !ticketRepository.existsById(payment.getTicket().getId())) {
            return Optional.empty();
        }
        if (payment.getUser() != null && !userRepository.existsById(payment.getUser().getId())) {
            return Optional.empty();
        }
        return Optional.of(paymentRepository.save(payment));
    }

    public Optional<Payment> updatePayment(Long id, Payment updatedPayment) {
        if (!paymentRepository.existsById(id)) {
            return Optional.empty();
        }
        if (updatedPayment.getTicket() == null || !ticketRepository.existsById(updatedPayment.getTicket().getId())) {
            return Optional.empty();
        }
        if (updatedPayment.getUser() != null && !userRepository.existsById(updatedPayment.getUser().getId())) {
            return Optional.empty();
        }
        updatedPayment.setId(id);
        return Optional.of(paymentRepository.save(updatedPayment));
    }

    public boolean deletePayment(Long id) {
        if (paymentRepository.existsById(id)) {
            paymentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}