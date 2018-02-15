/**
 * 
 */
package books.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.google.common.collect.Lists;
import books.converter.BookConverter;
import books.json.BookJson;
import books.model.Author;
import books.model.Book;
import books.model.Genre;
import books.repository.AuthorRepository;
import books.repository.BookRepository;
import books.repository.GenreRepository;

/**
 * @author tiago.gashu
 */
@Component
@Transactional
public class BookService {

  @Autowired
  private BookConverter converter;
  @Autowired
  private BookRepository repo;
  @Autowired
  private GenreRepository genreRepo;
  @Autowired
  private AuthorRepository authorRepo;

  @Transactional(readOnly = true)
  public List<BookJson> findAll() {
    Sort sort = new Sort(Direction.ASC, "name");
    return this.converter.convertToJsons(this.repo.findAll(sort));
  }

  @Transactional(readOnly = true)
  public BookJson find(Long bookId) {
    Book book = this.repo.findOne(bookId);
    return this.converter.convertToJson(book);
  }

  public List<BookJson> findByName(String bookName) {
    List<Book> books = this.repo.findByName(bookName);
    return this.converter.convertToJsons(books);
  }

  public List<BookJson> findByAuthorName(String authorName) {
    List<Book> books = this.repo.findByName(authorName);
    return this.converter.convertToJsons(books);
  }

  // TODO
  public List<BookJson> findByNameAndAuthorName(String name, String authorName) {
    List<Book> books = Lists.newArrayList();
    // Lists.newArrayList(this.repo.findByNameAndAuthorNameIgnoreCase(name, authorName));
    return this.converter.convertToJsons(books);
  }

  public BookJson save(BookJson json) {
    Book entity = this.converter.convertToEntity(json);

    List<Genre> genres = entity.getGenres();
    for (Genre genre : genres) {
      this.genreRepo.save(genre);
    }

    List<Author> authors = entity.getAuthors();
    for (Author author : authors) {
      this.authorRepo.save(author);
    }

    this.repo.save(entity);

    return this.converter.convertToJson(this.repo.findOne(entity.getId()));
  }

  public void delete(List<Long> bookIds) {
    for (Long id : bookIds) {
      this.repo.delete(id);
    }
  }
}
