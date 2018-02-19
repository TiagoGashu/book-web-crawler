package books.repository;

import org.springframework.data.repository.CrudRepository;
import books.model.Book;

/**
 * {@link CrudRepository} para a classe {@link Book}
 * 
 * @author tiago.gashu
 *
 */
public interface BookRepository extends BookBaseRepository<Book>, BookCustomRepository {

}
