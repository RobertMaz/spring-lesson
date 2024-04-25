package org.example.hibernateexample.dao;

import org.example.hibernateexample.entity.Book;
import org.springframework.stereotype.Repository;

@Repository
public class BookDao extends AbstractHibernateDao<Book> {

    public BookDao() {
        setClazz(Book.class);
    }
}
