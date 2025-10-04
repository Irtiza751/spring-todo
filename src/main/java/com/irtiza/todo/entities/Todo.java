package com.irtiza.todo.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "todos")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 255)
    private String description;

    private boolean isCompleted;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public Todo() {}

    public Todo(int id, String name, String description, boolean isCompleted, Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isCompleted = isCompleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Todo(String name, String description, boolean isCompleted) {
        this.name = name;
        this.description = description;
        this.isCompleted = isCompleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
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
}
