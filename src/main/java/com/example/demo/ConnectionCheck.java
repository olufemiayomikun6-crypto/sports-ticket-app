package com.example.demo;

import org.bson.Document;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class ConnectionCheck implements CommandLineRunner {

    private final MongoTemplate mongoTemplate;

    public ConnectionCheck(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void run(String... args) {
        try {
            mongoTemplate.getDb().runCommand(new Document("ping", 1));
            System.out.println("Connected to database: " + mongoTemplate.getDb().getName());
        } catch (Exception e) {
            System.out.println("Connection FAILED: " + e.getMessage());
        }
    }
}