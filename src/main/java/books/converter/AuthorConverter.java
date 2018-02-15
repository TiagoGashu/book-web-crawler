/**
 * 
 */
package books.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import books.json.AuthorJson;
import books.model.Author;
import books.repository.AuthorRepository;
import generics.converter.Converter;

/**
 * @author tiago.gashu
 *
 */
@Component
public class AuthorConverter extends Converter<Author, AuthorJson> {

  @Autowired
  private AuthorRepository authorRepo;

  /*
   * (non-Javadoc)
   * 
   * @see generics.converter.Converter#convertToJson(java.lang.Object)
   */
  @Override
  public AuthorJson convertToJson(Author entity) {
    AuthorJson json = new AuthorJson();
    json.setId(entity.getId());
    json.setName(entity.getName());
    return json;
  }

  /*
   * (non-Javadoc)
   * 
   * @see generics.converter.Converter#convertToEntity(java.io.Serializable)
   */
  @Override
  public Author convertToEntity(AuthorJson json) {
    Author author = null;
    if (json.getId() != null) {
      author = this.authorRepo.findOne(json.getId());
      author.setName(json.getName());
    } else {
      if (json.getName() != null) {
        author = this.authorRepo.findByName(json.getName());
      }

      // nova instancia
      if (author == null) {
        author = new Author(null, json.getName());
      }
    }
    return author;
  }

}
