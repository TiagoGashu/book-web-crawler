/**
 * @author tiago.gashu
 */
package books.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.google.common.collect.Lists;

/**
 * @author tiago.gashu
 */
@Entity
@Table(name = "BOOK")
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(name = "book_seq", sequenceName = "book_seq", initialValue = 1,
    allocationSize = 50)
public class Book {

  private Long id;
  private List<Genre> genres = Lists.newArrayList();
  private String name;
  private List<Author> authors = Lists.newArrayList();
  private List<Chapter> chapters = Lists.newArrayList();

  /** */
  public Book() {
    super();
  }

  /**
   * @param id
   * @param genres
   * @param name
   * @param authorName
   * @param authorsName
   */
  public Book(Long id, List<Genre> genres, String name, List<Author> authors) {
    super();
    this.id = id;
    this.genres = genres;
    this.name = name;
    this.authors = authors;
  }

  /**
   * @param id
   * @param genres
   * @param name
   * @param authorName
   * @param authorsName
   * @param chapters
   */
  public Book(Long id, List<Genre> genres, String name, List<Author> authors,
      List<Chapter> chapters) {
    super();
    this.id = id;
    this.genres = genres;
    this.name = name;
    this.authors = authors;
    this.chapters = chapters;
  }

  // GETTERS / SETTERS

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @OneToMany
  @JoinTable(name = "BOOK_GENRE", joinColumns = {@JoinColumn(name = "BOOK_ID")},
      inverseJoinColumns = {@JoinColumn(name = "GENRE_ID")})
  public List<Genre> getGenres() {
    return genres;
  }

  public void setGenres(List<Genre> genres) {
    this.genres = genres;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @OneToMany
  @JoinTable(name = "BOOK_AUTHOR", joinColumns = {@JoinColumn(name = "BOOK_ID")},
      inverseJoinColumns = {@JoinColumn(name = "AUTHOR_ID")})
  public List<Author> getAuthors() {
    return authors;
  }

  public void setAuthors(List<Author> authors) {
    this.authors = authors;
  }

  @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
  @OrderBy("ORDEM")
  public List<Chapter> getChapters() {
    return chapters;
  }

  public void setChapters(List<Chapter> chapters) {
    for (Chapter ch : chapters) {
      ch.setBook(this);
    }
    this.chapters = chapters;
  }

}
