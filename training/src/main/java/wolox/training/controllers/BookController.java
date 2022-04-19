package wolox.training.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import wolox.training.exceptions.BookAlreadyOwnedException;
import wolox.training.exceptions.BookIdMismatchException;
import wolox.training.exceptions.BookNotFoundException;
import wolox.training.models.Book;
import wolox.training.models.User;
import wolox.training.repositories.BookRepository;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private BookRepository bookRepository;

    /**
     * This method creates a greeting view with the following attributes:
     * @param name: Name of the person (String)
     * @param model: Object (Object)
     * @return String "greeting"
     */
    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }


    /** Method to get the books
     * @return
     */
    @GetMapping
    public Iterable findAll() {
        return bookRepository.findAll();
    }

    /** Method to find by author
     * @param bookAuthor: String
     * @return
     * */
    @GetMapping("/author/{bookAuthor}")
    public List findByAuthor(@PathVariable String bookAuthor) {
        return bookRepository.findByAuthor(bookAuthor);
    }

    /** Method to find by id
     * @param id: Long
     * @return findById
     * @exception BookNotFoundException
     * */
    @GetMapping("/books/{id}")
    public Book findOne(@PathVariable Long id) {
        return bookRepository.findById(id)
                .orElseThrow(BookNotFoundException::new);
    }

    /** Method to create a book
     * @param book: Book
     * @return save
     **/
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    /** Method to delete by id
     * @param id: Long
     * @exception BookNotFoundException
     * */
    @DeleteMapping("/books/{id}")
    public void delete(@PathVariable Long id) {
        bookRepository.findById(id)
                .orElseThrow(BookNotFoundException::new);
        bookRepository.deleteById(id);
    }

    /** Method to update a book
     * @param book: Book
     * @param id: Long
     * @exception BookIdMismatchException
     * @exception BookNotFoundException
     * @return save
     * */
    @PutMapping("/books/{id}")
    public Book updateBook(@RequestBody Book book, @PathVariable Long id, @PathVariable User user) {
        if (book.getId() != id) {
            throw new BookIdMismatchException();
        }
        if (user.getBooks() == book){
            throw new BookAlreadyOwnedException();
        }
        bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException());
        return bookRepository.save(book);
    }

}
