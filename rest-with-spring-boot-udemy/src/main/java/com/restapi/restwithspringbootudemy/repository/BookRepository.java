package com.restapi.restwithspringbootudemy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restapi.restwithspringbootudemy.data.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
