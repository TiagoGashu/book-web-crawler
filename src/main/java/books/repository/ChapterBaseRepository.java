/**
 * 
 */
package books.repository;

import org.springframework.data.repository.CrudRepository;
import books.model.Chapter;

/**
 * @author tiago.gashu
 */
public interface ChapterBaseRepository<T extends Chapter> extends CrudRepository<T, Long> {

}
