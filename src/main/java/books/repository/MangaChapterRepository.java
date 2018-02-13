/**
 * 
 */
package books.repository;

import org.springframework.data.repository.CrudRepository;
import books.model.MangaChapter;

/**
 * @author tiago.gashu
 */
public interface MangaChapterRepository extends CrudRepository<MangaChapter, Long> {

}
