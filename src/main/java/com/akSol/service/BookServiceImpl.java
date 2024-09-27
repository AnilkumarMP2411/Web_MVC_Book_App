package com.akSol.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akSol.entity.Book;
import com.akSol.repository.BookRepo;



@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepo repo;
	
	
	public List<Book> getAllBooks() {
		return  repo.findByActiveSW("Y");
//		return repo.findAll();
	}


	@Override
	public boolean saveBook(Book book) {
		
		book.setActiveSW("Y");
		
		Book savedBook = repo.save(book);
		if(savedBook.getBookId()!=null) {
			return true;
		}
		
		return false;
		
//		return savedBook.getBookId()!=null;
	}


	@Override
	public void deleteBook(Integer bookId) {
		//hard delete
//		 repo.deleteById(bookId);
		
//		soft delete
		Optional<Book> findById = repo.findById(bookId);
		if(findById.isPresent()) {
			Book book = findById.get();
			book.setActiveSW("N");
			repo.save(book);
		}
	}


	@Override
	public Book getBookById(Integer bookId) {
		// TODO Auto-generated method stub
		Optional<Book> findById = repo.findById(bookId);
		if(findById.isPresent()) {
			return findById.get();
		}		
		return null;
	}

}
