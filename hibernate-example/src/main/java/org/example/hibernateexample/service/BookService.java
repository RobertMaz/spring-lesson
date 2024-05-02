package org.example.hibernateexample.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.example.hibernateexample.dao.BookRepository;
import org.example.hibernateexample.dao.BookSpecification;
import org.example.hibernateexample.dao.CustomerRepository;
import org.example.hibernateexample.entity.Book;
import org.example.hibernateexample.entity.Customer;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final CustomerRepository customerRepository;
    private final BookService bookService;

    public BookService(BookRepository bookRepository, CustomerRepository customerRepository, @Lazy BookService bookService) {
        this.bookRepository = bookRepository;
        this.customerRepository = customerRepository;
        this.bookService = bookService;
    }

    @Transactional
    public void saveBooks(List<Book> books, Customer customer) {
        //
        List<Book> all = bookRepository.findAll(new BookSpecification());
        for (Book book : all) {
            System.out.println(book);
        }
    }

    public void someMethod(List<Book> books, Customer customer){
        bookService.saveBooks(books, customer);
    }
}
