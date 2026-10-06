package com.libraryManagementSystem.repository;

import com.libraryManagementSystem.model.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(Book book) {
        entityManager.persist(book);
    }

    public Book findById(long id) {
        return entityManager.find(Book.class,id);
    }

    public List<Book> findAll() {
        return entityManager.createQuery("select b from Book b",Book.class).getResultList();
    }
}
