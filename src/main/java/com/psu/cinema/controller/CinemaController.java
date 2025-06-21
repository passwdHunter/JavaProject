package com.psu.cinema.controller;

import com.psu.cinema.entity.Cinema;
import com.psu.cinema.service.CinemaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cinemas")
@Tag(name = "Cinema API", description = "API for managing cinemas in the system")
public class CinemaController {

    private final CinemaService cinemaService;

    @Autowired
    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @Operation(summary = "Get all cinemas", description = "Retrieves a list of all cinemas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of cinemas")
    })
    @GetMapping
    public List<Cinema> getAllCinemas() {
        return cinemaService.getAllCinemas();
    }

    @Operation(summary = "Get cinema by ID", description = "Retrieves a cinema by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved cinema"),
            @ApiResponse(responseCode = "404", description = "Cinema not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Cinema> getCinemaById(
            @Parameter(description = "ID of the cinema to retrieve") @PathVariable Long id) {
        return cinemaService.getCinemaById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new cinema", description = "Creates a new cinema in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created cinema"),
            @ApiResponse(responseCode = "400", description = "Invalid cinema data")
    })
    @PostMapping
    public ResponseEntity<Cinema> createCinema(
            @Parameter(description = "Cinema data to create") @Valid @RequestBody Cinema cinema) {
        Cinema createdCinema = cinemaService.createCinema(cinema);
        return ResponseEntity.ok(createdCinema);
    }

    @Operation(summary = "Update a cinema", description = "Updates an existing cinema by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated cinema"),
            @ApiResponse(responseCode = "404", description = "Cinema not found"),
            @ApiResponse(responseCode = "400", description = "Invalid cinema data")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Cinema> updateCinema(
            @Parameter(description = "ID of the cinema to update") @PathVariable Long id,
            @Parameter(description = "Updated cinema data") @Valid @RequestBody Cinema cinema) {
        return cinemaService.updateCinema(id, cinema)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a cinema", description = "Deletes a cinema by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted cinema"),
            @ApiResponse(responseCode = "404", description = "Cinema not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCinema(
            @Parameter(description = "ID of the cinema to delete") @PathVariable Long id) {
        if (cinemaService.deleteCinema(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}