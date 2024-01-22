package com.casestudy.bookservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Book {

    @Id
    private String bookId;
    private String name;
    private String author;
    private Integer copiesAvailable;
    private Integer totalCopies;

    public Book() {
        super();
    }

    public Book(String bookId, String name, String author, Integer copiesAvailable, Integer totalCopies) {
        this.bookId = bookId;
        this.name = name;
        this.author = author;
        this.copiesAvailable = copiesAvailable;
        this.totalCopies = totalCopies;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getCopiesAvailable() {
        return copiesAvailable;
    }

    public void setCopiesAvailable(Integer copiesAvailable) {
        this.copiesAvailable = copiesAvailable;
    }

    public Integer getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(Integer totalCopies) {
        this.totalCopies = totalCopies;
    }
}
