/**
 * :P
 */
package books.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author tiago.gashu
 */
@Entity
@Table(name = "CHAPTER")
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(name = "chapter_seq", sequenceName = "chapter_seq", initialValue = 1,
    allocationSize = 50)
public class Chapter implements Comparable<Chapter> {

  private Long id;
  private Book book;
  // nome do capítulo
  private String chapterName;
  private Long ordem;

  /** */
  public Chapter() {
    super();
  }

  /**
   * @param id
   * @param chapterName
   * @param ordem
   */
  public Chapter(Long id, String chapterName, Long ordem) {
    super();
    this.id = id;
    this.chapterName = chapterName;
    this.ordem = ordem;
  }

  /**
   * @param id
   * @param book
   * @param chapterName
   * @param ordem
   */
  public Chapter(Long id, Book book, String chapterName, Long ordem) {
    super();
    this.id = id;
    this.book = book;
    this.chapterName = chapterName;
    this.ordem = ordem;
  }

  // GETTERS / SETTERS

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chapter_seq")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "BOOK_ID", nullable = false)
  public Book getBook() {
    return book;
  }

  public void setBook(Book book) {
    this.book = book;
  }

  @Column(name = "CHAPTER_NAME")
  public String getChapterName() {
    return chapterName;
  }

  public void setChapterName(String chapterName) {
    this.chapterName = chapterName;
  }

  @Column(name = "ORDEM")
  public Long getOrdem() {
    return ordem;
  }

  public void setOrdem(Long ordem) {
    this.ordem = ordem;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Comparable#compareTo(java.lang.Object)
   */
  @Override
  public int compareTo(Chapter o) {
    return this.ordem.compareTo(o.ordem);
  }

}
