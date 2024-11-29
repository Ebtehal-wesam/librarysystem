package com.example.librarysystem.Service;
import com.example.librarysystem.Exceptions.BookNotFoundExcption;
import com.example.librarysystem.Repository.BookRepository;
import com.example.librarysystem.Exceptions.BorrRecNotFoundExcption;
import com.example.librarysystem.Repository.BorrRecRepository;
import com.example.librarysystem.Entity.BorrRec;
import com.example.librarysystem.Exceptions.PatronNotFoundExcption;
import com.example.librarysystem.Repository.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
public class BorrRecService {
    @Autowired
    private BorrRecRepository borrrecrepo ;
    @Autowired
    private BookRepository bookrepo;
    @Autowired
    private PatronRepository patronrepo;

    public void borrowaBook(Long bookid, Long patronid) {
         bookrepo.findById(bookid).orElseThrow(() -> new BookNotFoundExcption("book not found"));
        patronrepo.findById(patronid).orElseThrow(() -> new PatronNotFoundExcption("patron not found"));

        BorrRec br = new BorrRec();
        br.setBookid(bookid);
        br.setPatron(patronid);
        br.setBorrow_date(LocalDate.now());

        borrrecrepo.save(br);
    }

    public void returnBook(Long bookid, Long patronid) {
        BorrRec br = borrrecrepo.findByBookId_AndPatronId(bookid, patronid)
                .orElseThrow(() -> new BorrRecNotFoundExcption("Borrowing record not found"));

        br.setReturn_date(LocalDate.now());
        borrrecrepo.save(br);
    }
}