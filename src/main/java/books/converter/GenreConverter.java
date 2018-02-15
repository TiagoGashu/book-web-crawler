/**
 * 
 */
package books.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import books.json.GenreJson;
import books.model.Genre;
import books.repository.GenreRepository;
import generics.converter.Converter;

/**
 * @author tiago.gashu
 *
 */
@Component
public class GenreConverter extends Converter<Genre, GenreJson> {

  @Autowired
  private GenreRepository genreRepo;

  /*
   * (non-Javadoc)
   * 
   * @see generics.converter.Converter#convertToJson(java.lang.Object)
   */
  @Override
  public GenreJson convertToJson(Genre entity) {
    GenreJson json = new GenreJson();
    json.setId(entity.getId());
    json.setGenre(entity.getGenre());
    return json;
  }

  /*
   * (non-Javadoc)
   * 
   * @see generics.converter.Converter#convertToEntity(java.io.Serializable)
   */
  @Override
  public Genre convertToEntity(GenreJson json) {
    Genre genre = null;
    if (json.getId() != null) {
      genre = this.genreRepo.findOne(json.getId());
      genre.setGenre(json.getGenre());
    } else {
      if (json.getGenre() != null) {
        genre = this.genreRepo.findByGenre(json.getGenre());
      }

      // nova instancia
      if (genre == null) {
        genre = new Genre(null, json.getGenre());
      }
    }
    return genre;
  }

}
