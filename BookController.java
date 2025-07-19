package com.example.bookstore.controller;

import com.example.bookstore.model.Book;
import com.example.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import javax.persistence.criteria.Predicate;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepository repo;

    @GetMapping
    public Page<Book> getAll(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Book create(@RequestBody Book book) {
        return repo.save(book);
    }

    @PutMapping("/{id}")
    public Book update(@PathVariable Long id, @RequestBody Book book) {
        book.setId(id);
        return repo.save(book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }

    @GetMapping("/search")
    public Page<Book> search(@RequestParam Optional<String> title,
                             @RequestParam Optional<String> authorName,
                             Pageable pageable) {
        return repo.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            title.ifPresent(t -> predicates.add(cb.like(root.get("title"), "%" + t + "%")));
            authorName.ifPresent(a -> predicates.add(cb.like(root.get("author").get("name"), "%" + a + "%")));
            return cb.and(predicates.toArray(new Predicate[0]));
        }, pageable);
    }
}