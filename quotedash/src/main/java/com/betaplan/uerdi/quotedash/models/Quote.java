package com.betaplan.uerdi.quotedash.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Date;

@Entity
@Table(name = "quotes")
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Author is required")
    @Size(min = 3, message = "Author must be at least 3 characters")
    private String author;

    @NotEmpty(message = "Quote is required")
    @Size(min = 8, message = "Quote must be at least 8 characters")
    private String quote;

    @Column(updatable = false)
    private Date createdAt;

    private Date updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }

    // connection many to one
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User lead;

    // empty and loaded constructors
    public Quote() {
    }

    public Quote(Long id, String author, String quote, User lead) {
        this.id = id;
        this.author = author;
        this.quote = quote;
        this.lead = lead;
    }

    // Getter and setter methods

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public User getLead() {
        return lead;
    }

    public void setLead(User lead) {
        this.lead = lead;
    }
}


