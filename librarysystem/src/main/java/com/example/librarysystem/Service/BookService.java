package com.example.librarysystem.Service;

import com.example.librarysystem.Repository.BookRepository;
import com.example.librarysystem.Entity.Book;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository ;
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public List<Book> getBooks(){
        return bookRepository.findAll();

    }
    public Book getBookById(Long id) {
        Optional<Book> optionalbook = bookRepository.findById(id);

        if (optionalbook.isPresent()) {
            return optionalbook.get();
        } else {
            throw new IllegalStateException("book with ID " +id+" is not found");
        }
    }


    public void addNewBook(Book b) {
        bookRepository.save(b);
    }

    public void deleteBook(Long bookid) {
        boolean b = bookRepository.existsById(bookid);
        if (!b){
            throw new IllegalStateException("book doesn't exist : "+bookid);
        }else {
            bookRepository.deleteById(bookid);
        }
    }
//    @Transactional

    public Book updateBook(Long id, Book book ) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book existing = optionalBook.get();
            if (book.getTitle() != null ){
                existing.setTitle(book.getTitle());
            }
            if (book.getAuthor() != null){
                existing.setAuthor(book.getAuthor());

            }
            if (book.getPublication_year() != null ){
                existing.setPublication_year(book.getPublication_year());

            }
            if (book.getISBN() != null){
                existing.setISBN(book.getISBN());

            }
            return bookRepository.save(existing);
        } else {
            throw new EntityNotFoundException("Book not found with id " + id);
        }
    }
}
