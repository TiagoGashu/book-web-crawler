package books.converter;

import org.springframework.stereotype.Component;
import books.json.BookJson;
import books.model.Book;
import generics.converter.Converter;

/**
 * @author tiago.gashu
 */
@Component
public class BookConverter extends Converter<Book, BookJson> {

  // TO JSON METHODS

  public BookJson convertToJson(Book book) {
    BookJson json = new BookJson();
    json.setId(book.getId());
    json.setName(book.getName());
    // TODO
    // json.setAuthorName(book.getAuthor().getName());
    return json;
  }

  // TO ENTITY METHODS

  public Book convertToEntity(BookJson json) {
    Book book = new Book();
    book.setId(json.getId());
    book.setName(json.getName());
    // TODO
    // book.setAuthorName(json.getAuthorName());
    return book;
  }

}
