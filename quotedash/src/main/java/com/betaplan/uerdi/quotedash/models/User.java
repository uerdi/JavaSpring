package com.betaplan.uerdi.quotedash.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name is required")
    @Size(min = 4, max = 12, message = "Name must be between 4 and 12 characters")
    private String userName;

    @NotEmpty(message = "Email is required")
    @Email(message = "Please enter a valid email")
    @Size(message = "Please enter e valid email")
    private String email;

    @NotEmpty(message = "Password is required")
    @Size(min = 8, message ="Password must be between 5 and 20 characters" )
    private String password;

    @Transient
    @NotEmpty(message = "Confirm password is required")
    @Size(min = 8, message = "Confirm password must be between 5 and 20 characters")
    private String confirm;


    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;


    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }

    // connections one to many user with quotes
    @OneToMany(mappedBy = "lead", fetch = FetchType.LAZY)
    private List<Quote> quoteLead;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_quotes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "quote_id")
    )
    private List<Quote> likedQuotes;
    public User() {

    }

    public User(Long id, String userName, String email, String password, String confirm, Date createdAt, Date updatedAt, List<Quote> quoteLead, List<Quote> likedQuotes) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.confirm = confirm;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.quoteLead = quoteLead;
        this.likedQuotes = likedQuotes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Quote> getQuoteLead() {
        return quoteLead;
    }

    public void setQuoteLead(List<Quote> quoteLead) {
        this.quoteLead = quoteLead;
    }

    public List<Quote> getLikedQuotes() {
        return likedQuotes;
    }

    public void setLikedQuotes(List<Quote> likedQuotes) {
        this.likedQuotes = likedQuotes;
    }
}