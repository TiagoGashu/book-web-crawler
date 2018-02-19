/**
 * 
 */
package books.repository;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import books.model.Book;

/**
 * @author tiago.gashu
 */
public interface BookBaseRepository<T extends Book> extends CrudRepository<T, Long> {

  List<T> findAll(Sort sort);

  List<T> findByName(String name);

  // TODO
  // List<Book> findByAuthorName(String authorName);

  // @Query("SELECT b FROM Book b WHERE UPPER(b.name) = UPPER(:name) AND UPPER(b.authorName) =
  // UPPER(:authorName) ORDER BY b.name ASC")
  // Book findByNameAndAuthorNameIgnoreCase(@Param("name") String name,
  // @Param("authorName") String authorName);

}
