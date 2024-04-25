package org.example.hibernateexample.schedule;

import org.example.hibernateexample.dao.BookDao;
import org.example.hibernateexample.entity.Book;
import org.example.hibernateexample.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import org.hibernate.query.criteria.JpaRoot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class Scheduler {

    public Scheduler(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    private final BookDao bookDao;

    @Scheduled(fixedDelay = Long.MAX_VALUE)
    public void schedule(){
        bookDao.update(new Book("Java book", "Description sample"));
        System.out.println(bookDao.findAll());
    }

}
