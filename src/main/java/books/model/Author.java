/**
 * :P
 */
package books.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author tiago.gashu
 */
@Entity
@Table(name = "AUTHOR")
@SequenceGenerator(name = "author_seq", sequenceName = "author_seq", initialValue = 1,
    allocationSize = 50)
public class Author {

  private Long id;
  private String name;

  public Author() {
    super();
  }

  /**
   * @param id
   * @param name
   */
  public Author(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  // GETTERS / SETTERS

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_seq")
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

}
