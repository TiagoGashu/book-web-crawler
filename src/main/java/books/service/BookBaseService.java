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
import books.converter.AuthorConverter;
import books.converter.GenreConverter;
import books.json.BookJson;
import books.model.Author;
import books.model.Book;
import books.model.Genre;
import books.repository.AuthorRepository;
import books.repository.BookBaseRepository;
import books.repository.GenreRepository;
import generics.converter.Converter;

/**
 * @author tiago.gashu
 */
@Component
@Transactional
public abstract class BookBaseService<T extends Book, J extends BookJson> {

  @Autowired
  protected GenreRepository genreRepo;
  @Autowired
  protected GenreConverter genreConverter;
  @Autowired
  protected AuthorRepository authorRepo;
  @Autowired
  protected AuthorConverter authorConverter;

  protected abstract Converter<T, J> getConverter();

  protected abstract BookBaseRepository<T> getRepository();

  @Transactional(readOnly = true)
  public List<J> findAll() {
    Sort sort = new Sort(Direction.ASC, "name");
    return this.getConverter().convertToJsons(this.getRepository().findAll(sort));
  }

  @Transactional(readOnly = true)
  public J find(Long bookId) {
    T book = this.getRepository().findOne(bookId);
    return this.getConverter().convertToJson(book);
  }

  public List<J> findByName(String bookName) {
    List<T> books = this.getRepository().findByName(bookName);
    return this.getConverter().convertToJsons(books);
  }

  public List<J> findByAuthorName(String authorName) {
    List<T> books = this.getRepository().findByName(authorName);
    return this.getConverter().convertToJsons(books);
  }

  // TODO
  public List<J> findByNameAndAuthorName(String name, String authorName) {
    List<T> books = Lists.newArrayList();
    // Lists.newArrayList(this.repo.findByNameAndAuthorNameIgnoreCase(name, authorName));
    return this.getConverter().convertToJsons(books);
  }

  public J save(J json) {
    Converter<T, J> converter = this.getConverter();
    BookBaseRepository<T> repository = this.getRepository();

    T entity = converter.convertToEntity(json);
    this.saveTransientProps(entity, json);
    repository.save(entity);
    T savedEntity = repository.findOne(entity.getId());
    return converter.convertToJson(savedEntity);
  }

  private void saveTransientProps(T entity, J json) {
    List<Genre> genres = entity.getGenres();
    for (Genre genre : genres) {
      this.genreRepo.save(genre);
    }

    List<Author> authors = entity.getAuthors();
    for (Author author : authors) {
      this.authorRepo.save(author);
    }

    json.setAuthors(this.authorConverter.convertToJsons(entity.getAuthors()));
    json.setGenres(this.genreConverter.convertToJsons(entity.getGenres()));
  }

  public List<J> delete(List<Long> bookIds) {
    List<J> arr = Lists.newArrayList();
    for (Long id : bookIds) {
      J json = this.getConverter().convertToJson(this.getRepository().findOne(id));
      arr.add(json);
      this.getRepository().delete(id);
    }
    return arr;
  }

}
