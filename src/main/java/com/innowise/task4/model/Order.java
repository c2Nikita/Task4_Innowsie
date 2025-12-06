package com.innowise.task4.model;

import java.util.Objects;

public class Order {

    private Long id;
    private Long userId;
    private Long CourierId;
    private String description;
    private boolean completed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCourierId() {
        return CourierId;
    }

    public void setCourierId(Long courierId) {
        CourierId = courierId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return completed == order.completed && Objects.equals(id, order.id) && Objects.equals(userId, order.userId) && Objects.equals(CourierId, order.CourierId) && Objects.equals(description, order.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, CourierId, description, completed);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", CourierId=" + CourierId +
                ", description='" + description + '\'' +
                ", completed=" + completed +
                '}';
    }
}
