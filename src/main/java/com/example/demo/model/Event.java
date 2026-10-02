package com.example.demo.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("events")
public class Event {

    @Id
    private String id;

    private String name;
    private String sport;
    private String venue;
    private LocalDateTime eventDate;
    private String description;
    private double ticketPrice;
    private int availableTickets;

    public Event() {
    }

    public Event(String name, String sport, String venue, LocalDateTime eventDate,
                 String description, double ticketPrice, int availableTickets) {
        this.name = name;
        this.sport = sport;
        this.venue = venue;
        this.eventDate = eventDate;
        this.description = description;
        this.ticketPrice = ticketPrice;
        this.availableTickets = availableTickets;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSport() { return sport; }
    public void setSport(String sport) { this.sport = sport; }

    public String getVenue() { return venue; }
    public void setVenue(String venue) { this.venue = venue; }

    public LocalDateTime getEventDate() { return eventDate; }
    public void setEventDate(LocalDateTime eventDate) { this.eventDate = eventDate; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getTicketPrice() { return ticketPrice; }
    public void setTicketPrice(double ticketPrice) { this.ticketPrice = ticketPrice; }

    public int getAvailableTickets() { return availableTickets; }
    public void setAvailableTickets(int availableTickets) { this.availableTickets = availableTickets; }
}
