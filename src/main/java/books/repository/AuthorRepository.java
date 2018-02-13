/**
 * 
 */
package books.repository;

import org.springframework.data.repository.CrudRepository;
import books.model.Author;

/**
 * @author tiago.gashu
 */
public interface AuthorRepository extends CrudRepository<Author, Long> {

  public Author findByName(String name);

}
