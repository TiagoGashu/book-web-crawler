/**
 * 
 */
package books.json;

import java.io.Serializable;

/**
 * @author tiago.gashu
 */
public class GenreJson implements Serializable {

  /** */
  private static final long serialVersionUID = -6657611066135290652L;

  private Long id;
  private String genre;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getGenre() {
    return genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

}
