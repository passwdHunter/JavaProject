package com.psu.cinema.controller;

import com.psu.cinema.entity.Ticket;
import com.psu.cinema.service.TicketService;
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
@RequestMapping("/api/tickets")
@Tag(name = "Ticket API", description = "API for managing tickets in the system")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Operation(summary = "Get all tickets", description = "Retrieves a list of all tickets")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of tickets")
    })
    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @Operation(summary = "Get ticket by ID", description = "Retrieves a ticket by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved ticket"),
            @ApiResponse(responseCode = "404", description = "Ticket not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketById(
            @Parameter(description = "ID of the ticket to retrieve") @PathVariable Long id) {
        return ticketService.getTicketById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new ticket", description = "Creates a new ticket in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created ticket"),
            @ApiResponse(responseCode = "400", description = "Invalid ticket data or session/user not found")
    })
    @PostMapping
    public ResponseEntity<Ticket> createTicket(
            @Parameter(description = "Ticket data to create") @Valid @RequestBody Ticket ticket) {
        return ticketService.createTicket(ticket)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Update a ticket", description = "Updates an existing ticket by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated ticket"),
            @ApiResponse(responseCode = "404", description = "Ticket not found"),
            @ApiResponse(responseCode = "400", description = "Invalid ticket data or session/user not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Ticket> updateTicket(
            @Parameter(description = "ID of the ticket to update") @PathVariable Long id,
            @Parameter(description = "Updated ticket data") @Valid @RequestBody Ticket ticket) {
        return ticketService.updateTicket(id, ticket)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a ticket", description = "Deletes a ticket by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted ticket"),
            @ApiResponse(responseCode = "404", description = "Ticket not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(
            @Parameter(description = "ID of the ticket to delete") @PathVariable Long id) {
        if (ticketService.deleteTicket(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}