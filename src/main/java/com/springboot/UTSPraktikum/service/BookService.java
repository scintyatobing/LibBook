package com.springboot.UTSPraktikum.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springboot.UTSPraktikum.model.Book;
import com.springboot.UTSPraktikum.repository.BookRepository;


@Service
@Transactional
public class BookService{
	@Autowired
	private BookRepository repos;

	public List<Book> getAllBook() {
		return repos.findAll();
	}

	public void saveBook(Book book) {
		repos.save(book);
	}

	public Book getBookById(Integer book_id) {
		return repos.findById(book_id).get();
	}

	public Book updateBook(Book book) {
		return repos.save(book);
	}

	public void deleteBookById(Integer book_id) {
		repos.deleteById(book_id);	
	}

}
