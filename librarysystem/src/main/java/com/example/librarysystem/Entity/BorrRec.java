package com.example.librarysystem.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity(name="borrrec")
@Table
public class BorrRec {
    @Id
    @SequenceGenerator(
            name = "borrrec_sequence",
            sequenceName = "borrrec_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "borrrec_sequence"
    )
    // book id , patron id, borrow date, return date, return status
    private Long br_id ;

    @ManyToOne
    @JoinColumn(name = "Id", nullable = false) //
    private Book book ;
    private Long bookId ;
    private Long patronId ;
    private LocalDate borrow_date;
    private LocalDate return_date;

    public BorrRec() {
    }

    public Long getBookid() {
        return bookId;
    }

    public void setBookid(Long bookid) {
        this.bookId = bookid;
    }

    public Long getPatron() {
        return patronId;
    }

    public void setPatron(Long patron) {
        this.patronId = patron;
    }

    public LocalDate getBorrow_date() {
        return borrow_date;
    }

    public void setBorrow_date(LocalDate borrow_date) {
        this.borrow_date = borrow_date;
    }

    public LocalDate getReturn_date() {
        return return_date;
    }

    public void setReturn_date(LocalDate return_date) {
        this.return_date = return_date;
    }


    public BorrRec(Long br_id, Long bookid, Long patron, LocalDate borrow_date, LocalDate return_date) {
        this.br_id = br_id;
        this.bookId = bookid;
        this.patronId = patron;
        this.borrow_date = borrow_date;
        this.return_date = return_date;

    }

    public BorrRec(Long bookid, Long patron, LocalDate borrow_date, LocalDate return_date) {
        this.bookId = bookid;
        this.patronId = patron;
        this.borrow_date = borrow_date;
        this.return_date = return_date;

    }
}
