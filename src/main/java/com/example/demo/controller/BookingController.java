package com.example.demo.controller;

import com.example.demo.dto.BookingRequest;
import com.example.demo.model.Booking;
import com.example.demo.model.Event;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.EventRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "http://localhost:5173")
public class BookingController {

    private final BookingRepository bookingRepository;
    private final EventRepository eventRepository;

    public BookingController(BookingRepository bookingRepository, EventRepository eventRepository) {
        this.bookingRepository = bookingRepository;
        this.eventRepository = eventRepository;
    }

    @PostMapping
    public ResponseEntity<?> bookTicket(@RequestBody BookingRequest request) {
        if (request.quantity() < 1) {
            return ResponseEntity.badRequest().body(Map.of("error", "Quantity must be at least 1"));
        }

        Optional<Event> eventOpt = eventRepository.findById(request.eventId());
        if (eventOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Event not found"));
        }

        Event event = eventOpt.get();
        if (event.getAvailableTickets() < request.quantity()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Not enough tickets available"));
        }

        String userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        double totalPrice = event.getTicketPrice() * request.quantity();
        Booking booking = new Booking(userId, event.getId(), request.quantity(), totalPrice);
        Booking saved = bookingRepository.save(booking);

        event.setAvailableTickets(event.getAvailableTickets() - request.quantity());
        eventRepository.save(event);

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/my")
    public ResponseEntity<List<Booking>> myBookings() {
        String userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(bookingRepository.findByUserId(userId));
    }
}