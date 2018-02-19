/**
 * 
 */
package books.converter;

import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import books.json.ChapterJson;
import books.json.MangaChapterJson;
import books.model.MangaChapter;
import books.repository.MangaChapterRepository;
import generics.converter.Converter;

/**
 * @author tiago.gashu
 */
@Component
public class MangaChapterConverter extends Converter<MangaChapter, MangaChapterJson> {

  @Autowired
  private ChapterConverter chapterConverter;
  @Autowired
  private MangaPageConverter pageConverter;
  @Autowired
  private MangaChapterRepository mangaChapterRepo;

  /*
   * @see generics.converter.Converter#convertToJson(java.lang.Object)
   */
  @Override
  public MangaChapterJson convertToJson(MangaChapter entity) {
    ChapterJson chapterJson = this.chapterConverter.convertToJson(entity);
    MangaChapterJson json = new MangaChapterJson(chapterJson);
    json.setDateAdded(entity.getDateAdded());
    json.setSource(entity.getSource());
    json.setPages(this.pageConverter.convertToJsons(entity.getPages()));
    return json;
  }

  /*
   * @see generics.converter.Converter#convertToEntity(java.io.Serializable)
   */
  @Override
  public MangaChapter convertToEntity(MangaChapterJson json) {
    MangaChapter chapter = null;
    if (json.getId() != null) {
      chapter = this.mangaChapterRepo.findOne(json.getId());
    } else {
      chapter = new MangaChapter();
    }
    chapter.setChapterName(json.getChapterName());
    chapter.setOrdem(json.getOrdem());
    chapter.setDateAdded(json.getDateAdded());
    chapter.setSource(json.getSource());

    chapter.refreshAndAddAll(this.pageConverter.convertToEntities(json.getPages()));
    Collections.sort(chapter.getPages());

    return chapter;
  }

}
