/**
 * 
 */
package books.json;

import java.io.Serializable;

/**
 * @author tiago.gashu
 */
public class ChapterJson implements Serializable {

  /** */
  private static final long serialVersionUID = 7070420684603459696L;

  private Long id;
  private String chapterName;
  private Long ordem;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getChapterName() {
    return chapterName;
  }

  public void setChapterName(String chapterName) {
    this.chapterName = chapterName;
  }

  public Long getOrdem() {
    return ordem;
  }

  public void setOrdem(Long ordem) {
    this.ordem = ordem;
  }

}
