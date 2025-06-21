package com.psu.cinema.controller;

import com.psu.cinema.entity.Review;
import com.psu.cinema.service.ReviewService;
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
@RequestMapping("/api/reviews")
@Tag(name = "Review API", description = "API for managing reviews in the system")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Operation(summary = "Get all reviews", description = "Retrieves a list of all reviews")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of reviews")
    })
    @GetMapping
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @Operation(summary = "Get review by ID", description = "Retrieves a review by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved review"),
            @ApiResponse(responseCode = "404", description = "Review not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(
            @Parameter(description = "ID of the review to retrieve") @PathVariable Long id) {
        return reviewService.getReviewById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new review", description = "Creates a new review in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created review"),
            @ApiResponse(responseCode = "400", description = "Invalid review data or film/user not found")
    })
    @PostMapping
    public ResponseEntity<Review> createReview(
            @Parameter(description = "Review data to create") @Valid @RequestBody Review review) {
        return reviewService.createReview(review)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Update a review", description = "Updates an existing review by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated review"),
            @ApiResponse(responseCode = "404", description = "Review not found"),
            @ApiResponse(responseCode = "400", description = "Invalid review data or film/user not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(
            @Parameter(description = "ID of the review to update") @PathVariable Long id,
            @Parameter(description = "Updated review data") @Valid @RequestBody Review review) {
        return reviewService.updateReview(id, review)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a review", description = "Deletes a review by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted review"),
            @ApiResponse(responseCode = "404", description = "Review not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(
            @Parameter(description = "ID of the review to delete") @PathVariable Long id) {
        if (reviewService.deleteReview(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.noContent().build();
    }
}