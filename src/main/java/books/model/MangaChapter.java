/**
 * :P
 */
package books.model;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Representa um capítulo de um {@link Manga}
 * 
 * @author tiago.gashu
 */
@Entity
@Table(name = "MANGA_CHAPTER")
@PrimaryKeyJoinColumn(name = "ID")
public class MangaChapter extends Chapter {

  private Manga manga;
  private List<MangaPage> pages;
  private Date dateAdded;

  /** */
  public MangaChapter() {
    super();
  }

  /**
   * @param pages
   * @param dateAdded
   */
  public MangaChapter(Long id, Manga manga, String chapterName, Long order, List<MangaPage> pages,
      Date dateAdded) {
    super(id, chapterName, order);
    super.setBook((Book) manga);
    for (MangaPage page : pages) {
      page.setMangaChapter(this);
    }
    this.pages = pages;
    this.dateAdded = dateAdded;
  }

  // GETTERS / SETTERS

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "MANGA_ID")
  public Manga getManga() {
    return manga;
  }

  public void setManga(Manga manga) {
    this.manga = manga;
  }

  @OneToMany(mappedBy = "mangaChapter", cascade = CascadeType.ALL)
  @OrderBy("ORDEM")
  public List<MangaPage> getPages() {
    return pages;
  }

  public void setPages(List<MangaPage> pages) {
    for (MangaPage page : pages) {
      page.setMangaChapter(this);
    }
    this.pages = pages;
  }

  @Column(name = "DATE_ADDED")
  public Date getDateAdded() {
    return dateAdded;
  }

  public void setDateAdded(Date dateAdded) {
    this.dateAdded = dateAdded;
  }

}
