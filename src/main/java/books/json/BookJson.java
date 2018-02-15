package books.json;

import java.io.Serializable;
import java.util.List;
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
  private List<AuthorJson> authors;
  private List<GenreJson> genres;
  private List<ChapterJson> chapters;

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

  public List<AuthorJson> getAuthors() {
    return authors;
  }

  public void setAuthors(List<AuthorJson> authors) {
    this.authors = authors;
  }

  public List<GenreJson> getGenres() {
    return genres;
  }

  public void setGenres(List<GenreJson> genres) {
    this.genres = genres;
  }

  public List<ChapterJson> getChapters() {
    return chapters;
  }

  public void setChapters(List<ChapterJson> chapters) {
    this.chapters = chapters;
  }

}
