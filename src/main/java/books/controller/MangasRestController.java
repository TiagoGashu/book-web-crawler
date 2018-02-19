/**
 * 
 */
package books.controller;

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
import books.json.MangaJson;
import books.model.Manga;
import books.service.MangaService;

/**
 * @author tiago.gashu
 */
@RestController
public class MangasRestController extends BooksBaseRestController<MangaService, Manga, MangaJson> {

  @Autowired
  private MangaService mangaService;

  // endpoints GET

  @GetMapping(path = "/mangas/{mangaId}")
  public ResponseEntity<MangaJson> getMangaById(
      @PathVariable(name = "mangaId", required = true) Long mangaId) {
    MangaJson json = this.find(mangaId);
    return new ResponseEntity<MangaJson>(json, HttpStatus.OK);
  }

  @GetMapping(path = "/mangas")
  public ResponseEntity<List<MangaJson>> getBooks(
      @RequestParam(name = "name", required = false) String name,
      @RequestParam(name = "authorName", required = false) String authorName) {
    List<MangaJson> jsons = this.fetchBooksBy(name, authorName);
    return new ResponseEntity<List<MangaJson>>(jsons, HttpStatus.OK);
  }

  // endpoints POST

  @PostMapping(path = "/mangas")
  public ResponseEntity<MangaJson> createOrUpdate(@RequestBody MangaJson json) {
    MangaJson retJson = null;

    try {
      retJson = this.save(json);
    } catch (Exception e) {
      return new ResponseEntity<MangaJson>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    return new ResponseEntity<MangaJson>(retJson, HttpStatus.OK);
  }

  // endpoinst PUT

  @PutMapping(path = "/mangas")
  public ResponseEntity<MangaJson> update(@RequestBody MangaJson json) {
    // TODO
    return new ResponseEntity<>(HttpStatus.OK);
  }

  // endpoints DELETE

  @DeleteMapping(path = "/mangas")
  public ResponseEntity<List<MangaJson>> doDelete(
      @RequestParam(name = "bookIds") List<Long> bookIds) {
    List<MangaJson> arr = this.delete(bookIds);
    return new ResponseEntity<List<MangaJson>>(arr, HttpStatus.OK);
  }

  /*
   * @see books.controller.BooksBaseRestController#getService()
   */
  @Override
  protected MangaService getService() {
    return this.mangaService;
  }

}
