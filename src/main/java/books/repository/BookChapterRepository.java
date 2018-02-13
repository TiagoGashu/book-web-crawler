/**
 * 
 */
package books.repository;

import org.springframework.data.repository.CrudRepository;
import books.model.Chapter;

/**
 * @author tiago.gashu
 */
public interface BookChapterRepository extends CrudRepository<Chapter, Long> {

}
