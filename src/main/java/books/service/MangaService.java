/**
 * 
 */
package books.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import books.converter.MangaConverter;
import books.json.MangaJson;
import books.model.Manga;
import books.repository.BookBaseRepository;
import books.repository.MangaRepository;
import generics.converter.Converter;

/**
 * @author tiago.gashu
 */
@Component
@Transactional
public class MangaService extends BookBaseService<Manga, MangaJson> {

  @Autowired
  private MangaConverter converter;
  @Autowired
  private MangaRepository repo;

  /*
   * @see books.service.BookBaseService#getConverter()
   */
  @Override
  protected Converter<Manga, MangaJson> getConverter() {
    return this.converter;
  }

  /*
   * @see books.service.BookBaseService#getRepository()
   */
  @Override
  protected BookBaseRepository<Manga> getRepository() {
    return this.repo;
  }

}
