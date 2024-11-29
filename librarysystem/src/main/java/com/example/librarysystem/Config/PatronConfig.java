package com.example.librarysystem.Config;

import com.example.librarysystem.Entity.Patron;
import com.example.librarysystem.Repository.PatronRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
@Configuration
// attributes like ID, title, author, publication year, ISBN, etc

public class PatronConfig {
    @Bean
    CommandLineRunner commandLineRunn(PatronRepository repositr){
        return args -> {
            Patron b1 = new Patron("sam","383838383");
            Patron b2 = new Patron("kim","kim@hotmail.com");

            repositr.saveAll(
                    List.of(b1,b2)
            );

        };
    }
}
