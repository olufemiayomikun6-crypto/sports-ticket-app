package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Event;

public interface EventRepository extends MongoRepository<Event, String> {

    List<Event> findBySport(String sport);
}