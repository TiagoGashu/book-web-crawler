/**
 * :P
 */
package books.repository;

import org.springframework.data.repository.CrudRepository;
import books.model.Manga;

/**
 * @author tiago.gashu
 *
 */
public interface MangaRepository extends CrudRepository<Manga, Long> {

}
