/**
 * 
 */
package books.model;

import java.util.Date;
import javax.persistence.EntityManager;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.google.common.collect.Lists;
import books.json.AuthorJson;
import books.json.BookJson;
import books.json.ChapterJson;
import books.json.GenreJson;
import books.json.MangaChapterJson;
import books.json.MangaJson;
import books.json.MangaPageJson;
import books.service.BookService;
import books.service.MangaService;

/**
 * @author tiago.gashu
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class MangaServiceTest {

  @Autowired
  private EntityManager em;
  @Autowired
  private BookService bookService;
  @Autowired
  private MangaService mangaService;

  @Test
  public void test_persist_book() {
    AuthorJson a1 = new AuthorJson();
    a1.setName("Togashi Yoshihiro");

    AuthorJson a2 = new AuthorJson();
    a2.setName("Takeuchi Naoko");

    GenreJson g1 = new GenreJson();
    g1.setGenre("Shounen");

    GenreJson g2 = new GenreJson();
    g2.setGenre("Fantasy");

    ChapterJson c1 = new ChapterJson();
    c1.setChapterName("1");
    c1.setOrdem(1L);

    ChapterJson c2 = new ChapterJson();
    c2.setChapterName("2");
    c2.setOrdem(2L);

    BookJson bookJson = new BookJson();

    bookJson.setName("Hunter x Hunter");
    bookJson.setAuthors(Lists.newArrayList(a1, a2));
    bookJson.setGenres(Lists.newArrayList(g1, g2));
    bookJson.setChapters(Lists.newArrayList(c1, c2));

    BookJson ret = this.bookService.save(bookJson);

    // assert

    this.em.flush();

    BookJson found = this.bookService.find(ret.getId());

    Assert.assertEquals(ret.getId(), found.getId());

  }

  @Test
  public void test_persist_manga() {
    AuthorJson a1 = new AuthorJson();
    a1.setName("Togashi Yoshihiro");

    AuthorJson a2 = new AuthorJson();
    a2.setName("Takeuchi Naoko");

    GenreJson g1 = new GenreJson();
    g1.setGenre("Shounen");

    GenreJson g2 = new GenreJson();
    g2.setGenre("Fantasy");

    ChapterJson c1 = new ChapterJson();
    c1.setChapterName("1");
    c1.setOrdem(1L);

    // paginas do capitulo 1
    MangaPageJson ch1_p1 = new MangaPageJson();
    ch1_p1.setOrdem(1L);
    byte[] dados1 = new byte[1024];
    ch1_p1.setData(dados1);

    MangaPageJson ch1_p2 = new MangaPageJson();
    ch1_p2.setOrdem(2L);
    byte[] dados2 = new byte[2048];
    ch1_p2.setData(dados2);

    MangaChapterJson mc1 = new MangaChapterJson(c1);
    mc1.setDateAdded(new Date(0L));
    mc1.setSource("mangareader");
    mc1.setPages(Lists.newArrayList(ch1_p1, ch1_p2));

    // paginas do capitulo 2
    MangaPageJson c2_p1 = new MangaPageJson();
    c2_p1.setOrdem(1L);
    byte[] d21 = new byte[1000];
    c2_p1.setData(d21);

    MangaPageJson c2_p2 = new MangaPageJson();
    c2_p2.setOrdem(2L);
    byte[] d22 = new byte[2000];
    c2_p2.setData(d22);

    ChapterJson c2 = new ChapterJson();
    c2.setChapterName("2");
    c2.setOrdem(2L);

    MangaChapterJson mc2 = new MangaChapterJson(c2);
    mc2.setDateAdded(new Date(100L));
    mc2.setSource("mangareader");
    mc2.setPages(Lists.newArrayList(c2_p1, c2_p2));

    BookJson bookJson = new BookJson();

    bookJson.setName("Hunter x Hunter");
    bookJson.setAuthors(Lists.newArrayList(a1, a2));
    bookJson.setGenres(Lists.newArrayList(g1, g2));
    bookJson.setChapters(Lists.newArrayList(c1, c2));

    MangaJson mangaJson = new MangaJson(bookJson);
    mangaJson.setSource("mangareader");
    mangaJson.setCompleted(true);
    mangaJson.setChapters(Lists.newArrayList(c1, c2));
    mangaJson.setMangaChapters(Lists.newArrayList(mc1, mc2));

    MangaJson ret = this.mangaService.save(mangaJson);

    this.em.flush();

    MangaJson found = this.mangaService.find(ret.getId());

    Assert.assertEquals(ret.getId(), found.getId());
  }
}
