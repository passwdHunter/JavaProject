package com.psu.cinema.service;

import com.psu.cinema.entity.Review;
import com.psu.cinema.repository.FilmRepository;
import com.psu.cinema.repository.ReviewRepository;
import com.psu.cinema.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final FilmRepository filmRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, FilmRepository filmRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.filmRepository = filmRepository;
        this.userRepository = userRepository;
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Optional<Review> getReviewById(Long id) {
        return reviewRepository.findById(id);
    }

    public Optional<Review> createReview(Review review) {
        if (review.getFilm() == null || !filmRepository.existsById(review.getFilm().getId())) {
            return Optional.empty();
        }
        if (review.getUser() != null && !userRepository.existsById(review.getUser().getId())) {
            return Optional.empty();
        }
        return Optional.of(reviewRepository.save(review));
    }

    public Optional<Review> updateReview(Long id, Review updatedReview) {
        if (!reviewRepository.existsById(id)) {
            return Optional.empty();
        }
        if (updatedReview.getFilm() == null || !filmRepository.existsById(updatedReview.getFilm().getId())) {
            return Optional.empty();
        }
        if (updatedReview.getUser() != null && !userRepository.existsById(updatedReview.getUser().getId())) {
            return Optional.empty();
        }
        updatedReview.setId(id);
        return Optional.of(reviewRepository.save(updatedReview));
    }

    public boolean deleteReview(Long id) {
        if (reviewRepository.existsById(id)) {
            reviewRepository.deleteById(id);
            return true;
        }
        return false;
    }
}