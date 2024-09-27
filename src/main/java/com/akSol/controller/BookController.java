package com.akSol.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.akSol.entity.Book;
import com.akSol.service.BookService;

@Controller
public class BookController {

	@Autowired
	private BookService service;
	
	@GetMapping("/form")
	public ModelAndView form() {
		
		ModelAndView mav=new ModelAndView();
		
		//sending empty object for form binding		
		mav.addObject("book", new Book());
		mav.setViewName("form");
		return mav;	
	}
	
	@GetMapping("/edit")
	public ModelAndView editBook(@RequestParam("bookId")Integer bookId) {
		
		Book bookObj = service.getBookById(bookId);
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("book", bookObj);
		mav.setViewName("form");
		
		return mav;
	}
	
	@PostMapping("/book")
	public ModelAndView saveBook(Book book) {
		ModelAndView mav=new ModelAndView();
		
		boolean status=service.saveBook(book);
		if(status) {
			mav.addObject("succMsg", "Book Saved");
		}else {
			mav.addObject("errMsg", "Failed to Saved");
		}
		mav.setViewName("form");
		return mav;
	}
	
	@GetMapping("/books")
	public ModelAndView getBooks() {
		
		ModelAndView mav=new ModelAndView();
		List<Book> allBooks = service.getAllBooks();
		mav.addObject("books", allBooks);
		mav.setViewName("index");
		return mav;
		
	}
	
	@GetMapping("/delete")
	public ModelAndView deleteBook(@RequestParam("bookId") Integer bookId) {
		service.deleteBook(bookId);
		
		ModelAndView mav=new ModelAndView();
		List<Book> allBooks = service.getAllBooks();
		mav.addObject("books", allBooks);
		mav.setViewName("index");
		
		return mav;
	}
	
	
	}

