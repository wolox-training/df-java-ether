package wolox.training.models;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class User {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String username;

    @Column
    private String name;

    @Column
    private LocalDate birthdate;

    @OneToMany(mappedBy="user", fetch= FetchType.EAGER)
    @Column
    private List<Book> books = new ArrayList<>();

    public User() {
    }

    /*public User(List<Book> books) {
        if()
        //this.books = books;
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public List<Book> getBooks() {
        return (List<Book>) Collections.unmodifiableList(books);
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

}