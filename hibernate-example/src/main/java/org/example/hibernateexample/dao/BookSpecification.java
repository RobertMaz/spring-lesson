package org.example.hibernateexample.dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.example.hibernateexample.entity.Book;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecification implements Specification<Book> {

    @Override
    public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.and(root.get("id").in(1,2,3,4,5,7,8), criteriaBuilder.like(root.get("customer").get("name"), "John"));
    }
}
