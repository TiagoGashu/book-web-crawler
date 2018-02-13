package books.json;

import java.io.Serializable;
import books.model.Book;

/**
 * Representação JSON do {@link Book}
 * 
 * @author tiago.gashu
 */
public class BookJson implements Serializable {

  /** */
  private static final long serialVersionUID = -7218872037993993605L;

  private Long id;
  private String name;
  private String authorName;

  // GETTERS / SETTERS

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAuthorName() {
    return authorName;
  }

  public void setAuthorName(String authorName) {
    this.authorName = authorName;
  }



}
