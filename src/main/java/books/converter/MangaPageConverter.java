/**
 * 
 */
package books.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import books.json.MangaPageJson;
import books.model.MangaPage;
import books.repository.MangaPageRepository;
import generics.converter.Converter;

/**
 * @author tiago.gashu
 */
@Component
public class MangaPageConverter extends Converter<MangaPage, MangaPageJson> {

  @Autowired
  private MangaPageRepository repo;

  /*
   * @see generics.converter.Converter#convertToJson(java.lang.Object)
   */
  @Override
  public MangaPageJson convertToJson(MangaPage entity) {
    MangaPageJson json = new MangaPageJson();
    json.setId(entity.getId());
    json.setData(entity.getData());
    json.setOrdem(entity.getOrdem());
    return json;
  }

  /*
   * @see generics.converter.Converter#convertToEntity(java.io.Serializable)
   */
  @Override
  public MangaPage convertToEntity(MangaPageJson json) {
    MangaPage entity = null;
    if (json.getId() != null) {
      entity = this.repo.findOne(json.getId());
    } else {
      entity = new MangaPage();
    }
    entity.setOrdem(json.getOrdem());
    entity.setData(json.getData());
    return entity;
  }

}
