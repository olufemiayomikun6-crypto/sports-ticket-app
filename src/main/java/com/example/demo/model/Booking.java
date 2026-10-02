package com.example.demo.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("bookings")
public class Booking {

    private String id;
    private String userId;
    private String eventId;
    private int quantity;
    private double totalPrice;
    private LocalDateTime bookingDate;

    public Booking() {
    }

    public Booking(String userId, String eventId, int quantity, double totalPrice) {
        this.userId = userId;
        this.eventId = eventId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.bookingDate = LocalDateTime.now();
    }



    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getEventId() { return eventId; }
    public void setEventId(String eventId) { this.eventId = eventId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

    public LocalDateTime getBookingDate() { return bookingDate; }
    public void setBookingDate(LocalDateTime bookingDate) { this.bookingDate = bookingDate; }
}