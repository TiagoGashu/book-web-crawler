package books.controller;

import static org.springframework.util.StringUtils.isEmpty;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import books.json.BookJson;
import books.service.BookService;

/**
 * @author tiago.gashu
 */
@RestController
public class BooksRestController {

  @Autowired
  private BookService service;

  // endpoints GET

  @GetMapping(path = "/books/{bookId}")
  public ResponseEntity<BookJson> getBookById(
      @PathVariable(name = "bookId", required = true) Long bookId) {
    BookJson json = this.service.find(bookId);
    return new ResponseEntity<BookJson>(json, HttpStatus.OK);
  }

  @GetMapping(path = "/books")
  public ResponseEntity<List<BookJson>> getBooks(
      @RequestParam(name = "name", required = false) String name,
      @RequestParam(name = "authorName", required = false) String authorName) {
    List<BookJson> bookJsons = null;
    if (!isEmpty(name) && !isEmpty(authorName)) {
      bookJsons = this.service.findByNameAndAuthorName(name, authorName);
    } else if (!isEmpty(name)) {
      bookJsons = this.service.findByName(name);
    } else if (!isEmpty(authorName)) {
      bookJsons = this.service.findByAuthorName(authorName);
    } else {
      bookJsons = this.service.findAll();
    }
    return new ResponseEntity<List<BookJson>>(bookJsons, HttpStatus.OK);
  }

  // endpoints POST

  @PostMapping(path = "/books")
  public ResponseEntity<BookJson> createOrUpdate(@RequestBody BookJson json) {
    BookJson retJson = null;

    try {
      retJson = this.service.save(json);
    } catch (Exception e) {
      return new ResponseEntity<BookJson>(HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<BookJson>(retJson, HttpStatus.OK);
  }

  // endpoinst PUT

  @PutMapping(path = "/books")
  public ResponseEntity<BookJson> update(@RequestBody BookJson json) {
    // TODO
    return new ResponseEntity<>(HttpStatus.OK);
  }

  // endpoints DELETE

  @DeleteMapping(path = "/books")
  public ResponseEntity<BookJson> delete(@RequestParam(name = "bookIds") List<Long> bookIds) {
    this.service.delete(bookIds);
    return new ResponseEntity<BookJson>(new BookJson(), HttpStatus.OK);
  }

}
