package com.psu.cinema.controller;

import com.psu.cinema.entity.Session;
import com.psu.cinema.service.SessionService;
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
@RequestMapping("/api/sessions")
@Tag(name = "Session API", description = "API for managing movie sessions in the system")
public class SessionController {

    private final SessionService sessionService;

    @Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Operation(summary = "Get all sessions", description = "Retrieves a list of all movie sessions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of sessions")
    })
    @GetMapping
    public List<Session> getAllSessions() {
        return sessionService.getAllSessions();
    }

    @Operation(summary = "Get session by ID", description = "Retrieves a movie session by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved session"),
            @ApiResponse(responseCode = "404", description = "Session not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Session> getSessionById(
            @Parameter(description = "ID of the session to retrieve") @PathVariable Long id) {
        return sessionService.getSessionById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new session", description = "Creates a new movie session in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created session"),
            @ApiResponse(responseCode = "400", description = "Invalid session data or film/hall not found")
    })
    @PostMapping
    public ResponseEntity<Session> createSession(
            @Parameter(description = "Session data to create") @Valid @RequestBody Session session) {
        return sessionService.createSession(session)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Update a session", description = "Updates an existing movie session by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated session"),
            @ApiResponse(responseCode = "404", description = "Session not found"),
            @ApiResponse(responseCode = "400", description = "Invalid session data or film/hall not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Session> updateSession(
            @Parameter(description = "ID of the session to update") @PathVariable Long id,
            @Parameter(description = "Updated session data") @Valid @RequestBody Session session) {
        return sessionService.updateSession(id, session)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a session", description = "Deletes a movie session by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted session"),
            @ApiResponse(responseCode = "404", description = "Session not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSession(
            @Parameter(description = "ID of the session to delete") @PathVariable Long id) {
        if (sessionService.deleteSession(id)) {
            return ResponseEntity.noContent().build();}
        return ResponseEntity.notFound().build();
    }
}