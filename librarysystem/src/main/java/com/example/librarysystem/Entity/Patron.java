package com.example.librarysystem.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity(name="patron")
@Table
public class Patron {
    // like ID, name, contact information,
    @Id
    @SequenceGenerator(
            name = "patron_sequence",
            sequenceName = "patron_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "patron_sequence"
    )
    // attributes like ID, title, author, publication year, ISBN, etc
    private Long id ;
    @NotBlank(message = "name is required")

    private String name ;
    @NotBlank(message = "contact information is required")
    private String contact_info ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact_info() {
        return contact_info;
    }

    public void setContact_info(String contact_info) {
        this.contact_info = contact_info;
    }

    @Override
    public String toString() {
        return "Patron{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contact_info='" + contact_info + '\'' +
                '}';
    }

    public Patron() {
    }

    public Patron(Long id, String name, String contact_info) {
        this.id = id;
        this.name = name;
        this.contact_info = contact_info;
    }

    public Patron(String name, String contact_info) {
        this.name = name;
        this.contact_info = contact_info;
    }
}
