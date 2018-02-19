package books.converter;

import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import books.json.BookJson;
import books.model.Book;
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
  private ChapterConverter chapterConverter;
  @Autowired
  private BookRepository bookRepo;

  // TO JSON METHODS

  public BookJson convertToJson(Book book) {
    BookJson json = new BookJson();
    json.setId(book.getId());
    json.setName(book.getName());
    json.setAuthors(this.authorConverter.convertToJsons(book.getAuthors()));
    json.setGenres(this.genreConverter.convertToJsons(book.getGenres()));
    json.setChapters(this.chapterConverter.convertToJsons(book.getChapters()));
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

    book.refreshAndAddAllAuthors(this.authorConverter.convertToEntities(json.getAuthors()));
    book.refreshAndAddAllGenres(this.genreConverter.convertToEntities(json.getGenres()));
    book.refreshAndAddAllChapters(this.chapterConverter.convertToEntities(json.getChapters()));
    Collections.sort(book.getChapters());

    return book;
  }

}
