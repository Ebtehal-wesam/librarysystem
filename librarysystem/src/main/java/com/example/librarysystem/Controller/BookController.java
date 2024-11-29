package com.example.librarysystem.Controller;

import com.example.librarysystem.Entity.Book;
import com.example.librarysystem.Service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@RestController
@RequestMapping(path = "api/books")
@EnableWebMvc
public class BookController {
    private final BookService bookService ;
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping("/all")
    public List<Book> getBooks(){
        return bookService.getBooks();
    }
    @GetMapping("/all/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        try {
            Book book = bookService.getBookById(id);
            return ResponseEntity.ok(book);
        } catch (IllegalStateException e) {

            return ResponseEntity.notFound().build();
        }
//        catch (Exception e) {
//            // 4. Handle any other exceptions that may occur
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new ErrorResponse("Error retrieving student", e.getMessage()));
//        }
    }
    @PostMapping("/add")
    public void registerNewBook(@Valid @RequestBody Book book){
        bookService.addNewBook(book);
    }

    @DeleteMapping(path= "/delete/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") Long bookid){
        bookService.deleteBook(bookid);
        return ResponseEntity.noContent().build();
    }


    ///  String name, String model, String company

    @PutMapping(path="/update/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") Long bookid, @Valid @RequestBody Book book ) {
        Book newbook = bookService.updateBook(bookid, book );
        return new ResponseEntity<>(newbook , HttpStatus.OK);
    }



}
