/*
 * 
 */
package books.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import com.google.common.collect.Lists;

/**
 * @author tiago.gashu
 */
@Entity
@Table(name = "MANGA")
@PrimaryKeyJoinColumn(name = "ID")
public class Manga extends Book {

  private List<MangaChapter> mangaChapters = Lists.newArrayList();
  private String source;
  private boolean completed;

  /** */
  public Manga() {
    super();
  }

  /**
   * @param id
   * @param genres
   * @param name
   * @param authors
   * @param chapters
   * @param completed
   */
  public Manga(Long id, List<Genre> genres, String name, List<Author> authors,
      List<MangaChapter> chapters, String source, boolean completed) {
    super(id, genres, name, authors);
    this.refreshAndAddAllChapters(chapters);
    this.mangaChapters = chapters;
    this.completed = completed;
  }

  // GETTERS / SETTERS

  @Override
  public <T extends Chapter> void refreshAndAddAllChapters(List<T> chapters) {
    // super.refreshAndAddAllChapters(chapters);
    this.mangaChapters.clear();
    for (T ch : chapters) {
      if (ch instanceof MangaChapter) {
        MangaChapter mangaCh = (MangaChapter) ch;
        mangaCh.setManga(this);
        this.mangaChapters.add(mangaCh);
      }
    }
  }

  @OneToMany(mappedBy = "manga", cascade = CascadeType.ALL)
  @OrderBy("ORDEM")
  public List<MangaChapter> getMangaChapters() {
    return mangaChapters;
  }

  public void setMangaChapters(List<MangaChapter> mangaChapters) {
    List<Chapter> bookChapters = Lists.newArrayList();
    for (MangaChapter ch : mangaChapters) {
      ch.setManga(this);
      ch.setBook(this);
      bookChapters.add(ch);
    }
    super.setChapters(bookChapters);
    this.mangaChapters = mangaChapters;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public boolean isCompleted() {
    return completed;
  }

  public void setCompleted(boolean completed) {
    this.completed = completed;
  }

}
