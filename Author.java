package com.example.bookstore.model;

import javax.persistence.*;

@Entity
public class Author {
    @Id @GeneratedValue
    private Long id;
    private String name;

    public Author() {}
    public Author(String name) { this.name = name; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}