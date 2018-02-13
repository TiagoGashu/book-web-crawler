/**
 * 
 */
package books.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Representa o gênero de um {@link Book}
 * 
 * @author tiago.gashu
 */
@Entity
@Table(name = "GENRE")
@SequenceGenerator(name = "genre_seq", sequenceName = "genre_seq", initialValue = 1,
    allocationSize = 50)
public class Genre {

  private Long id;
  private String genre;

  /** */
  public Genre() {
    super();
  }

  /**
   * @param id
   * @param genre
   */
  public Genre(Long id, String genre) {
    this.id = id;
    this.genre = genre;
  }

  // GETTERS / SETTERS

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genre_seq")
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
