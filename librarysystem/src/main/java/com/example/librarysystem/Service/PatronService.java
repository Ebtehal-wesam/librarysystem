package com.example.librarysystem.Service;

import com.example.librarysystem.Entity.Patron;
import com.example.librarysystem.Repository.PatronRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.springframework.cache.annotation.Cacheable;
@Service
public class PatronService {
    private final PatronRepository patrepository ;
    @Autowired
    public PatronService(PatronRepository patrepository) {
        this.patrepository = patrepository;
    }


    public List<Patron> getPatrons(){
        return patrepository.findAll();

    }
    @Cacheable("patron")
    public Patron gePatronById(Long id) {
        Optional<Patron> optionalpat = patrepository.findById(id);

        if (optionalpat.isPresent()) {
            return optionalpat.get();
        } else {
            throw new IllegalStateException("patron with ID " +id+" is not found");
        }
    }


    public void addNewPatron(Patron b) {
        patrepository.save(b);
    }

    public void deletePatron(Long pid) {
        boolean b = patrepository.existsById(pid);
        if (!b){
            throw new IllegalStateException("patron doesn't exist : "+pid);
        }else {
            patrepository.deleteById(pid);
        }
    }
//    @Transactional

    public Patron updateBook(Long id, Patron patron ) {
        Optional<Patron> optionalPatron = patrepository.findById(id);
        if (optionalPatron.isPresent()) {
            Patron existing = optionalPatron.get();
            if (patron.getName() != null ){
                existing.setName(patron.getName());
            }
            if (patron.getContact_info() != null){
                existing.setContact_info(patron.getContact_info());

            }

            return patrepository.save(existing);
        } else {
            throw new EntityNotFoundException("patron not found with id " + id);
        }
    }
}
