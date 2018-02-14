package books.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import books.json.BookJson;
import books.model.Author;
import books.model.Book;
import books.repository.AuthorRepository;
import books.repository.BookRepository;
import generics.converter.Converter;

/**
 * @author tiago.gashu
 */
@Component
public class BookConverter extends Converter<Book, BookJson> {

  @Autowired
  private AuthorRepository authorRepo;
  @Autowired
  private BookRepository bookRepo;

  // TO JSON METHODS

  public BookJson convertToJson(Book book) {
    BookJson json = new BookJson();
    json.setId(book.getId());
    json.setName(book.getName());
    if (book.getAuthors().size() > 0) {
      json.setAuthorName(book.getAuthors().get(0).getName());
    }
    return json;
  }

  // TO ENTITY METHODS

  public Book convertToEntity(BookJson json) {
    Book book = new Book();
    Book found = null;
    if (json.getId() != null) {
      found = this.bookRepo.findOne(json.getId());
    }
    book.setId(json.getId());
    book.setName(json.getName());
    if (json.getAuthorName() != null) {
      String authorName = json.getAuthorName();
      Author author = this.authorRepo.findByName(authorName);
      if (author == null) {
        author = new Author(null, authorName);
      }
      book.getAuthors().add(author);
    }
    return book;
  }

}
