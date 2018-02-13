/**
 * :P
 */
package books.model;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.google.common.collect.Lists;
import books.repository.AuthorRepository;
import books.repository.BookChapterRepository;
import books.repository.BookRepository;
import books.repository.GenreRepository;
import books.repository.MangaRepository;

/**
 * @author tiago.gashu
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class MangaPersisterTest {

  private static final String AUTHOR_NAME = "Yoshihiro Togashi";

  @Autowired
  private EntityManager em;
  @Autowired
  private AuthorRepository authorRepository;
  @Autowired
  private GenreRepository bookGenreRepository;
  @Autowired
  private BookRepository bookRepository;
  @Autowired
  private BookChapterRepository bookChapterRepository;
  @Autowired
  private MangaRepository mangaRepository;

  @Test
  public void test_persist_book() {
    Author author = new Author(null, "Tokien");
    this.authorRepository.save(author);

    Genre genreFantasy = new Genre(null, "Fantasy");
    this.bookGenreRepository.save(genreFantasy);

    Book book = new Book();
    book.setName("O Senhor dos Anéis");
    this.bookRepository.save(book);

    Chapter ch1 = new Chapter(null, book, "Ch 1", 1L);
    Chapter ch2 = new Chapter(null, book, "Ch 2", 2L);
    this.bookChapterRepository.save(ch1);
    this.bookChapterRepository.save(ch2);

    book.setChapters(Lists.newArrayList(ch1, ch2));
    book.setAuthors(Lists.newArrayList(author));
    book.setGenres(Lists.newArrayList(genreFantasy));
    this.bookRepository.save(book);

    this.em.flush();

    Book found = this.bookRepository.findOne(book.getId());

    Assert.assertEquals(book.getId(), found.getId());

  }

  @Test
  public void test_persist_manga() {
    Author yoshihiroTogashi = new Author(null, AUTHOR_NAME);
    this.authorRepository.save(yoshihiroTogashi);

    Genre genreAction = new Genre(null, "Action");
    Genre genreFantasy = new Genre(null, "Fantasy");
    Genre genreShounen = new Genre(null, "Shounen");
    this.bookGenreRepository.save(genreAction);
    this.bookGenreRepository.save(genreFantasy);
    this.bookGenreRepository.save(genreShounen);

    this.em.flush();

    List<Genre> genres = Lists.newArrayList(genreAction, genreFantasy, genreShounen);

    Manga yuyuHakusho = new Manga();
    yuyuHakusho.setName("Yu Yu Hakusho");

    MangaPage ch1_page1 = new MangaPage(null, null, 1L, null);
    MangaPage ch1_page2 = new MangaPage(null, null, 2L, null);

    MangaPage ch2_page1 = new MangaPage(null, null, 1L, null);
    MangaPage ch2_page2 = new MangaPage(null, null, 2L, null);

    MangaChapter chapter1 = new MangaChapter(null, null, "chapter 1", 1L,
        Lists.<MangaPage>newArrayList(ch1_page1, ch1_page2), new Date(123L));

    MangaChapter chapter2 = new MangaChapter(null, null, "chapter 2", 2L,
        Lists.<MangaPage>newArrayList(ch2_page1, ch2_page2), new Date(234L));

    List<MangaChapter> mangaChapters = Lists.newArrayList(chapter1, chapter2);

    yuyuHakusho.setAuthors(Lists.newArrayList(yoshihiroTogashi));
    yuyuHakusho.setGenres(genres);
    yuyuHakusho.setMangaChapters(mangaChapters);

    this.mangaRepository.save(yuyuHakusho);

    this.em.flush();

    Manga found = this.mangaRepository.findOne(yuyuHakusho.getId());

    Book genericBook = (Book) found;

    this.em.flush();
  }

}
