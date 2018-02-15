package books.converter;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import books.json.BookJson;
import books.model.Author;
import books.model.Book;
import books.model.Genre;
import books.repository.BookRepository;
import generics.converter.Converter;

/**
 * @author tiago.gashu
 */
@Component
public class BookConverter extends Converter<Book, BookJson> {

  @Autowired
  private AuthorConverter authorConverter;
  @Autowired
  private GenreConverter genreConverter;
  @Autowired
  private BookRepository bookRepo;

  // TO JSON METHODS

  public BookJson convertToJson(Book book) {
    BookJson json = new BookJson();
    json.setId(book.getId());
    json.setName(book.getName());
    json.setAuthors(this.authorConverter.convertToJsons(book.getAuthors()));
    json.setGenres(this.genreConverter.convertToJsons(book.getGenres()));
    return json;
  }

  // TO ENTITY METHODS

  public Book convertToEntity(BookJson json) {
    Book book = null;
    if (json.getId() != null) {
      book = this.bookRepo.findOne(json.getId());
    } else {
      book = new Book();
    }
    book.setName(json.getName());

    book.getAuthors().clear();
    List<Author> authors = this.authorConverter.convertToEntities(json.getAuthors());
    for (Author author : authors) {
      book.getAuthors().add(author);
    }

    book.getGenres().clear();
    List<Genre> genres = this.genreConverter.convertToEntities(json.getGenres());
    for (Genre genre : genres) {
      book.getGenres().add(genre);
    }

    return book;
  }

}
