package wolox.training.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import wolox.training.models.Book;
import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    @Autowired
    List<Book> findByAuthor(String author);
}