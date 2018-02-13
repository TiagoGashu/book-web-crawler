package books.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.google.common.base.Objects;

/**
 * @author tiago.gashu
 */
@Entity
@Table(name = "MANGA_PAGE")
@SequenceGenerator(name = "manga_page_seq", sequenceName = "manga_page_seq", initialValue = 1,
    allocationSize = 50)
public class MangaPage implements Comparable<MangaPage> {

  private Long id;
  private MangaChapter mangaChapter;
  private Long ordem;
  private byte[] data;

  /** */
  public MangaPage() {
    super();
  }

  /**
   * @param id
   * @param mangaChapter
   * @param ordem
   * @param data
   */
  public MangaPage(Long id, MangaChapter mangaChapter, Long ordem, byte[] data) {
    super();
    this.id = id;
    this.mangaChapter = mangaChapter;
    this.ordem = ordem;
    this.data = data;
  }

  // GETTERS / SETTERS

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "manga_page_seq")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @ManyToOne
  @JoinColumn(name = "MANGA_CHAPTER_ID")
  public MangaChapter getMangaChapter() {
    return mangaChapter;
  }

  public void setMangaChapter(MangaChapter mangaChapter) {
    this.mangaChapter = mangaChapter;
  }

  @Column(name = "ORDEM")
  public Long getOrdem() {
    return ordem;
  }

  public void setOrdem(Long ordem) {
    this.ordem = ordem;
  }

  @Column(name = "DATA")
  @Lob
  public byte[] getData() {
    return data;
  }


  public void setData(byte[] data) {
    this.data = data;
  }

  // equals, hash-code e compareTo

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    MangaPage other = (MangaPage) obj;
    return Objects.equal(this.id, other.id);
  }

  @Override
  public int compareTo(MangaPage page) {
    return this.ordem.compareTo(page.ordem);
  }


}
