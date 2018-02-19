/**
 * 
 */
package books.converter;

import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import books.json.BookJson;
import books.json.MangaJson;
import books.model.Manga;
import books.repository.MangaRepository;
import generics.converter.Converter;

/**
 * @author tiago.gashu
 */
@Component
public class MangaConverter extends Converter<Manga, MangaJson> {

  @Autowired
  private MangaRepository repo;
  @Autowired
  private BookConverter bookConverter;
  @Autowired
  private MangaChapterConverter mangaChapterConverter;
  @Autowired
  private AuthorConverter authorConverter;
  @Autowired
  private GenreConverter genreConverter;

  /*
   * @see generics.converter.Converter#convertToJson(java.lang.Object)
   */
  @Override
  public MangaJson convertToJson(Manga entity) {
    BookJson bookJson = this.bookConverter.convertToJson(entity);
    MangaJson json = new MangaJson(bookJson);
    json.setSource(entity.getSource());
    json.setCompleted(entity.isCompleted());
    json.setMangaChapters(this.mangaChapterConverter.convertToJsons(entity.getMangaChapters()));
    return json;
  }

  /*
   * @see generics.converter.Converter#convertToEntity(java.io.Serializable)
   */
  @Override
  public Manga convertToEntity(MangaJson json) {
    Manga entity = null;
    if (json.getId() != null) {
      entity = this.repo.findOne(json.getId());
    } else {
      entity = new Manga();
    }
    entity.setName(json.getName());
    entity.refreshAndAddAllAuthors(this.authorConverter.convertToEntities(json.getAuthors()));
    entity.refreshAndAddAllGenres(this.genreConverter.convertToEntities(json.getGenres()));
    entity.refreshAndAddAllChapters(
        this.mangaChapterConverter.convertToEntities(json.getMangaChapters()));
    Collections.sort(entity.getChapters());
    entity.setCompleted(json.isCompleted());
    entity.setSource(json.getSource());
    return entity;
  }



}
