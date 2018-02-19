/**
 * 
 */
package books.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import books.json.ChapterJson;
import books.model.Chapter;
import books.model.MangaChapter;
import books.repository.ChapterRepository;
import generics.converter.Converter;

/**
 * @author tiago.gashu
 */
@Component
public class ChapterConverter extends Converter<Chapter, ChapterJson> {

  @Autowired
  private ChapterRepository repo;

  /*
   * @see generics.converter.Converter#convertToJson(java.lang.Object)
   */
  @Override
  public ChapterJson convertToJson(Chapter entity) {
    ChapterJson json = new ChapterJson();
    json.setId(entity.getId());
    json.setChapterName(entity.getChapterName());
    json.setOrdem(entity.getOrdem());
    return json;
  }

  /*
   * @see generics.converter.Converter#convertToEntity(java.io.Serializable)
   */
  @Override
  public Chapter convertToEntity(ChapterJson json) {
    Chapter chapter = null;
    if (json.getId() != null) {
      chapter = this.repo.findOne(json.getId());
    } else {
      chapter = new MangaChapter();
    }
    chapter.setChapterName(json.getChapterName());
    chapter.setOrdem(json.getOrdem());

    return chapter;
  }

}
