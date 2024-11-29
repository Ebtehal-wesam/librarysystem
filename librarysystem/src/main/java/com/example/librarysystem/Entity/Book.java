package com.example.librarysystem.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity(name="book")
@Table
public class Book {
    @Id
    @SequenceGenerator(
            name = "book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_sequence"
    )
    // attributes like ID, title, author, publication year, ISBN, etc

    private Long id ;
    @NotBlank(message = "Book title is required")
    private String title ;
    @NotBlank(message = "Book author is required")
    private String author ;
    @NotBlank(message = "publication year is required")
    @Size(min = 3, max = 5, message = "publication year must be between 3 and 5 digits")
    private String publication_year;
    private String ISBN;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BorrRec> borrowingRecordsList ;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublication_year() {
        return publication_year;
    }

    public void setPublication_year(String publication_year) {
        this.publication_year = publication_year;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publication_year='" + publication_year + '\'' +
                ", ISBN='" + ISBN + '\'' +
                '}';
    }

    public Book() {
    }

    public Book(Long id, String title, String author, String publication_year, String ISBN) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publication_year = publication_year;
        this.ISBN = ISBN;
    }

    public Book(String title, String author, String publication_year, String ISBN) {
        this.title = title;
        this.author = author;
        this.publication_year = publication_year;
        this.ISBN = ISBN;
    }
}
