/**
 * 
 */
package books.json;

import java.util.Date;
import java.util.List;

/**
 * @author tiago.gashu
 */
public class MangaChapterJson extends ChapterJson {

  /** */
  private static final long serialVersionUID = -5413029178308605930L;

  private List<MangaPageJson> pages;
  private String source;
  private Date dateAdded;

  public MangaChapterJson() {}

  public MangaChapterJson(ChapterJson chapterJson) {
    super.setId(chapterJson.getId());
    super.setChapterName(chapterJson.getChapterName());
    super.setOrdem(chapterJson.getOrdem());
  }

  public List<MangaPageJson> getPages() {
    return pages;
  }

  public void setPages(List<MangaPageJson> pages) {
    this.pages = pages;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public Date getDateAdded() {
    return dateAdded;
  }

  public void setDateAdded(Date dateAdded) {
    this.dateAdded = dateAdded;
  }

}
