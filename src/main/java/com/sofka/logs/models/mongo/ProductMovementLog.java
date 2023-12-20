package com.sofka.logs.models.mongo;

import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.Objects;

public class ProductMovementLog {
    @Id
    private String id;
    private String message;
    private Date date;

    public ProductMovementLog() {
    }

    public ProductMovementLog(String id, String message, Date date) {
        this.id = id;
        this.message = message;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductMovementLog that = (ProductMovementLog) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
