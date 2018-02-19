/**
 * 
 */
package books.controller;

import static org.springframework.util.StringUtils.isEmpty;
import java.util.List;
import books.json.BookJson;
import books.model.Book;
import books.service.BookBaseService;

/**
 * @author tiago.gashu
 * @param <T>
 * @param <J>
 */
public abstract class BooksBaseRestController<S extends BookBaseService<E, J>, E extends Book, J extends BookJson> {

  protected abstract S getService();

  public J find(Long bookId) {
    return this.getService().find(bookId);
  }

  public List<J> fetchBooksBy(String name, String authorName) {
    List<J> jsons = null;
    if (!isEmpty(name) && !isEmpty(authorName)) {
      jsons = this.getService().findByNameAndAuthorName(name, authorName);
    } else if (!isEmpty(name)) {
      jsons = this.getService().findByName(name);
    } else if (!isEmpty(authorName)) {
      jsons = this.getService().findByAuthorName(authorName);
    } else {
      jsons = this.getService().findAll();
    }
    return jsons;
  }

  public J save(J json) {
    return this.getService().save(json);
  }

  public List<J> delete(List<Long> ids) {
    return this.getService().delete(ids);
  }

}
