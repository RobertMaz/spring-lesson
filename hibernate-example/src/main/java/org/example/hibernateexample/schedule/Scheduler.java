package org.example.hibernateexample.schedule;

import org.example.hibernateexample.dao.BookRepository;
import org.example.hibernateexample.dao.CustomerRepository;
import org.example.hibernateexample.entity.Book;
import org.example.hibernateexample.entity.Customer;
import org.example.hibernateexample.service.BookService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Component
public class Scheduler {

    public Scheduler(BookService bookRepository, CustomerRepository customerRepository) {
        this.bookRepository = bookRepository;
        this.customerRepository = customerRepository;
    }

    private final BookService bookRepository;
    private final CustomerRepository customerRepository;

    /**
     * todo make it in migration
     */
    @Scheduled(fixedDelay = Long.MAX_VALUE)
    public void schedule(){
        Customer customer = new Customer();
        customer.setName("Bob");
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Book entity = new Book(i + "_Java book", i + "Description sample", customer, i * (new Random().nextInt() * 42));
            books.add(entity);
        }

        //
        bookRepository.someMethod(books, customer);
    }

}
