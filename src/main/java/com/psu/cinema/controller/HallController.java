package com.psu.cinema.controller;

import com.psu.cinema.entity.Hall;
import com.psu.cinema.service.HallService;
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
@RequestMapping("/api/halls")
@Tag(name = "Hall API", description = "API for managing cinema halls in the system")
public class HallController {

    private final HallService hallService;

    @Autowired
    public HallController(HallService hallService) {
        this.hallService = hallService;
    }

    @Operation(summary = "Get all halls", description = "Retrieves a list of all cinema halls")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of halls")
    })
    @GetMapping
    public List<Hall> getAllHalls() {
        return hallService.getAllHalls();
    }

    @Operation(summary = "Get hall by ID", description = "Retrieves a cinema hall by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved hall"),
            @ApiResponse(responseCode = "404", description = "Hall not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Hall> getHallById(
            @Parameter(description = "ID of the hall to retrieve") @PathVariable Long id) {
        return hallService.getHallById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new hall", description = "Creates a new cinema hall in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created hall"),
            @ApiResponse(responseCode = "400", description = "Invalid hall data or cinema not found")
    })
    @PostMapping
    public ResponseEntity<Hall> createHall(
            @Parameter(description = "Hall data to create") @Valid @RequestBody Hall hall) {
        return hallService.createHall(hall)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Update a hall", description = "Updates an existing cinema hall by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated hall"),
            @ApiResponse(responseCode = "404", description = "Hall not found"),
            @ApiResponse(responseCode = "400", description = "Invalid hall data or cinema not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Hall> updateHall(
            @Parameter(description = "ID of the hall to update") @PathVariable Long id,
            @Parameter(description = "Updated hall data") @Valid @RequestBody Hall hall) {
        return hallService.updateHall(id, hall)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a hall", description = "Deletes a cinema hall by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted hall"),
            @ApiResponse(responseCode = "404", description = "Hall not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHall(
            @Parameter(description = "ID of the hall to delete") @PathVariable Long id) {
        if (hallService.deleteHall(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}