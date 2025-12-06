package com.innowise.task4.model;

import java.util.Objects;

public class Courier {

    private Long id;

    private Long userId;

    private TransportType transportType;

    private double rating;

    private boolean active;

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public double getRating() {
        return rating;
    }
    public boolean getActive() {
        return active;
    }

    public boolean isActive() {
        return active;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setTransportType(TransportType transportType) {
        this.transportType = transportType;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Courier courier = (Courier) o;
        return Double.compare(rating, courier.rating) == 0 && active == courier.active && Objects.equals(id, courier.id) && Objects.equals(userId, courier.userId) && transportType == courier.transportType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, transportType, rating, active);
    }

    @Override
    public String toString() {
        return "Courier{" +
                "id=" + id +
                ", userId=" + userId +
                ", transportType=" + transportType +
                ", rating=" + rating +
                ", active=" + active +
                '}';
    }
}
