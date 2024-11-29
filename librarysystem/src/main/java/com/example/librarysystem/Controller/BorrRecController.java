package com.example.librarysystem.Controller;

import com.example.librarysystem.Service.BorrRecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@RequestMapping(path = "api")
@EnableWebMvc
public class BorrRecController {
    @Autowired
    private BorrRecService borrecser;

    @PostMapping("/borrow/{bookid}/patron/{patronid}")
    public ResponseEntity<Void> borrowaBook(@PathVariable("bookid") Long bookid, @PathVariable("patronid") Long patid) {
        borrecser.borrowaBook(bookid, patid);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/return/{bookid}/patron/{patronid}")
    public ResponseEntity<Void> returnBook(@PathVariable("bookid") Long bookid, @PathVariable("patronid") Long patid) {
        borrecser.returnBook(bookid, patid);
        return ResponseEntity.ok().build();
    }

}
////////////////////////////////////////