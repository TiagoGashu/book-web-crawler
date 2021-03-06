/**
 * 
 */
package books.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import books.converter.BookConverter;
import books.json.BookJson;
import books.model.Book;
import books.repository.BookBaseRepository;
import books.repository.BookRepository;
import generics.converter.Converter;

/**
 * @author tiago.gashu
 */
@Component
@Transactional
public class BookService extends BookBaseService<Book, BookJson> {

  @Autowired
  private BookConverter converter;
  @Autowired
  private BookRepository repo;

  /*
   * @see books.service.BookBaseService#getConverter()
   */
  @Override
  protected Converter<Book, BookJson> getConverter() {
    return this.converter;
  }

  /*
   * @see books.service.BookBaseService#getRepository()
   */
  @Override
  protected BookBaseRepository<Book> getRepository() {
    return this.repo;
  }
}
