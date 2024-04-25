package org.example.hibernateexample.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@NamedQueries({
        @NamedQuery(name="bookFinder", query = "from Book where name = :bookName")
})
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Integer cost;

    @Transient
    private String summary;
    @JoinColumn(name = "user_id")
    @ManyToOne(targetEntity = Customer.class)
    private Customer customer;

    public Book(String name, String description) {
        this();
        this.name = name;
        this.description = description;
    }

    public Book() {
        summary = name + " " + description + " : " + cost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                ", summary='" + summary + '\'' +
                '}';
    }
}
