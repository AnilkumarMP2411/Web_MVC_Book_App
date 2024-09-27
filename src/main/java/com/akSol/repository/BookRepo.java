package com.akSol.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akSol.entity.Book;

public interface BookRepo extends JpaRepository<Book, Integer>{

	public List<Book> findByActiveSW(String status);
}
