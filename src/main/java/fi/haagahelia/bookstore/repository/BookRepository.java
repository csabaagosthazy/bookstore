package fi.haagahelia.bookstore.repository;

import org.springframework.data.repository.CrudRepository;
import fi.haagahelia.bookstore.domain.Book;


public interface BookRepository extends CrudRepository<Book, Long> {

	Book findByTitle(String title);


}
