/**
 * 
 */
package books.repository;

import org.springframework.data.repository.CrudRepository;
import books.model.Genre;

/**
 * @author tiago.gashu
 */
public interface GenreRepository extends CrudRepository<Genre, Long> {

}
