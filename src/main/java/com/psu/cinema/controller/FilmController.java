package com.psu.cinema.controller;

import com.psu.cinema.entity.Film;
import com.psu.cinema.service.FilmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
        ("/api/films")
@Tag(name = "Film API", description = "API for managing films in the system")
public class FilmController {

    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @Operation(summary = "Get all films", description = "Retrieves a list of all films")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of films")
    })
    @GetMapping
    public List<Film> getAllFilms() {
        return filmService.getAllFilms();
    }

    @Operation(summary = "Get film by ID", description = "Retrieves a film by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved film"),
            @ApiResponse(responseCode = "404", description = "Film not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Film> getFilmById(
            @Parameter(description = "ID of the film to retrieve") @PathVariable Long id) {
        return filmService.getFilmById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new film", description = "Creates a new film in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created film"),
            @ApiResponse(responseCode = "400", description = "Invalid film data")
    })
    @PostMapping
    public ResponseEntity<Film> createFilm(
            @Parameter(description = "Film data to create") @Valid @RequestBody Film film) {
        Film createdFilm = filmService.createFilm(film);
        return ResponseEntity.ok(createdFilm);
    }

    @Operation(summary = "Update a film", description = "Updates an existing film by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated film"),
            @ApiResponse(responseCode = "404", description = "Film not found"),
            @ApiResponse(responseCode = "400", description = "Invalid film data")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Film> updateFilm(
            @Parameter(description = "ID of the film to update") @PathVariable Long id,
            @Parameter(description = "Updated film data") @Valid @RequestBody Film film) {
        return filmService.updateFilm(id, film)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a film", description = "Deletes a film by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted film"),
            @ApiResponse(responseCode = "404", description = "Film not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilm(
            @Parameter(description = "ID of the film to delete") @PathVariable Long id) {
        if (filmService.deleteFilm(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}