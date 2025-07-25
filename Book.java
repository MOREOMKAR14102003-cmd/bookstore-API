package com.example.bookstore.model;

import javax.persistence.*;

@Entity
public class Book {
    @Id @GeneratedValue
    private Long id;
    private String title;
    private String isbn;

    @ManyToOne
    private Author author;

    public Book() {}
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public Author getAuthor() { return author; }
    public void setAuthor(Author author) { this.author = author; }
}