package org.example.hibernateexample.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "app_users")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @OneToMany(mappedBy = "user_id", targetEntity = Book.class)
//    @JoinColumn(name = "user_id")
//    private List<Book> bookList;
}
