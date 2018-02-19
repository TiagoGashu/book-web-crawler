/**
 * 
 */
package books.json;

import java.util.List;

/**
 * @author tiago.gashu
 *
 */
public class MangaJson extends BookJson {

  /** */
  private static final long serialVersionUID = -6082380207078361379L;

  private List<MangaChapterJson> mangaChapters;
  private String source;
  private boolean completed;

  public MangaJson() {}

  public MangaJson(BookJson bookJson) {
    this.setId(bookJson.getId());
    this.setAuthors(bookJson.getAuthors());
    this.setGenres(bookJson.getGenres());
    this.setName(bookJson.getName());
  }

  public List<MangaChapterJson> getMangaChapters() {
    return mangaChapters;
  }

  public void setMangaChapters(List<MangaChapterJson> mangaChapters) {
    this.mangaChapters = mangaChapters;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public boolean isCompleted() {
    return completed;
  }

  public void setCompleted(boolean completed) {
    this.completed = completed;
  }

}
