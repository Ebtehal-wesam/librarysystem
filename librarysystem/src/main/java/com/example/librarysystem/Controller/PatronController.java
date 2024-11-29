package com.example.librarysystem.Controller;

import com.example.librarysystem.Entity.Patron;
import com.example.librarysystem.Service.PatronService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@RestController
@RequestMapping(path = "api/patron")
@EnableWebMvc
public class PatronController {
    private final PatronService patservice ;
    @Autowired
    public PatronController(PatronService patservice) {
        this.patservice = patservice;
    }
    @GetMapping("/all")
    public List<Patron> getPatrons(){
        return patservice.getPatrons();
    }
    @GetMapping("/all/{id}")
    public ResponseEntity<Patron> getPatronsById(@PathVariable Long id) {
        try {
            Patron p = patservice.gePatronById(id);
            return ResponseEntity.ok(p);
        } catch (IllegalStateException e) {
            // 3. Handle the case where the student is not found
            return ResponseEntity.notFound().build();
        }
//        catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new ErrorResponse("Error retrieving student", e.getMessage()));
//        }
    }
    @PostMapping("/add")
    public void registerNewPatron(@Valid @RequestBody Patron p){
        patservice.addNewPatron(p);
    }

    @DeleteMapping(path= "/delete/{id}")
    public ResponseEntity<Void> deletepatron(@PathVariable("id") Long pid){
        patservice.deletePatron(pid);
        return ResponseEntity.noContent().build();
    }


    ///  String name, String model, String company
    @PutMapping(path="/update/{id}")
    public ResponseEntity<Patron> updatePatron(@PathVariable("id") Long pid,@Valid @RequestBody Patron pat ) {
        Patron newpat = patservice.updateBook(pid, pat );
        return new ResponseEntity<>(newpat , HttpStatus.OK);
    }



}
