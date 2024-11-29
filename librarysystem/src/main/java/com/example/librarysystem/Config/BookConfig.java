package com.example.librarysystem.Config;

import com.example.librarysystem.Entity.Book;
import com.example.librarysystem.Repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
@Configuration
// attributes like ID, title, author, publication year, ISBN, etc

public class BookConfig {
    @Bean
    CommandLineRunner commandLineRunner(BookRepository repositry){
        return args -> {
            Book b1 = new Book("jungles","michel","2015","868436485857");
            Book b2 = new Book("secrects of life","sara","2011","747474747444");

            repositry.saveAll(
                    List.of(b1,b2)
            );

        };
    }
}
