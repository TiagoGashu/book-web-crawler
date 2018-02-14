package books.converter;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.google.common.collect.Lists;
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
    Book book = null;
    if (json.getId() != null) {
      book = this.bookRepo.findOne(json.getId());
    } else {
      book = new Book();
    }
    book.setName(json.getName());

    List<Author> authors = this.convertAuthors(json);

    book.getAuthors().clear();
    for (Author author : authors) {
      book.getAuthors().add(author);
    }

    return book;
  }

  private List<Author> convertAuthors(BookJson json) {
    List<Author> authors = Lists.newArrayList();
    if (json.getAuthorName() != null) {
      String authorName = json.getAuthorName();
      Author author = this.authorRepo.findByName(authorName);
      if (author == null) {
        author = new Author(null, authorName);
      }
      authors.add(author);
    }
    return authors;
  }

}
