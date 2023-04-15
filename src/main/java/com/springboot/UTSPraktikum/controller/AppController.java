package com.springboot.UTSPraktikum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.springboot.UTSPraktikum.model.Book;
import com.springboot.UTSPraktikum.model.User;
import com.springboot.UTSPraktikum.repository.BookRepository;
import com.springboot.UTSPraktikum.repository.UserRepository;
import com.springboot.UTSPraktikum.service.BookService;
 
@Controller
public class AppController {
	@Autowired
	private UserRepository repos;
	
	@Autowired
	private BookRepository book_repos;
	
	@Autowired
	private BookService bookService;
     
    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }
    
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
         
        return "signup_form";
    }
    
    @PostMapping("/proses_registrasi")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
         
        repos.save(user);
         
        return "register_success";
    }
    
    @GetMapping("/list/books")
    public String listBook(Model model) {
        List<Book> listBook = book_repos.findAll();
        model.addAttribute("listBook", listBook);
         
        return "list_book";
    }
    
    @GetMapping("/books")
    public String listBooks(Model model) {
        List<Book> listBooks = book_repos.findAll();
        model.addAttribute("listBooks", listBooks);
         
        return "books";
    }
    
	@GetMapping("/book/new")
	public String createBookForm(Model model) {
		Book book = new Book();
		model.addAttribute("book", book);
		return "create_book";
		
	}
	
	@PostMapping("/proses_create")
	public String saveBook(@ModelAttribute("book") Book book) {
		bookService.saveBook(book);
		return "redirect:books";
	}
	
	@GetMapping("/book/edit/{book_id}")
	public String editStudentForm(@PathVariable Integer book_id, Model model) {
		model.addAttribute("book", bookService.getBookById(book_id));
		return "edit_book";
	}

	@PostMapping("/book/{book_id}")
	public String updateBook(@PathVariable Integer book_id,
			@ModelAttribute("book") Book book,Model model) {

		Book existingBook = bookService.getBookById(book_id);
		existingBook.setBook_id(book_id);
		existingBook.setCategory_id(book.getCategory_id());
		existingBook.setTitle(book.getTitle());
		existingBook.setAuthor(book.getAuthor());
		existingBook.setPublisher(book.getPublisher());
		existingBook.setYear(book.getYear());
		
		bookService.updateBook(existingBook);
		return "redirect:/books";		
	}

	
	@GetMapping("/book/{book_id}")
	public String deleteBook(@PathVariable Integer book_id) {
		bookService.deleteBookById(book_id);
		return "redirect:/books";
	}	
	
	
	
	
}