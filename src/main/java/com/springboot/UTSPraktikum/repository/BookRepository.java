package com.springboot.UTSPraktikum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.UTSPraktikum.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{
	@Query("SELECT b FROM Book b WHERE b.book_id = ?1")
	Book findById(String book_id);
}
