/**
 * 
 */
package generics.converter;

import static org.springframework.util.CollectionUtils.isEmpty;
import java.io.Serializable;
import java.util.List;
import com.google.common.collect.Lists;

/**
 * @author tiago.gashu
 */
public abstract class Converter<E, J extends Serializable> {

  // TO JSON METHODS

  public abstract J convertToJson(E entity);

  public List<J> convertToJsons(List<E> entities) {
    List<J> jsons = Lists.newArrayList();

    if (isEmpty(entities)) {
      return jsons;
    }

    for (E entity : entities) {
      jsons.add(this.convertToJson(entity));
    }
    return jsons;
  }

  // TO ENTITY METHODS

  public abstract E convertToEntity(J json);

  public List<E> convertToEntities(List<J> jsons) {
    List<E> entities = Lists.newArrayList();

    if (isEmpty(jsons)) {
      return entities;
    }

    for (J json : jsons) {
      entities.add(this.convertToEntity(json));
    }
    return entities;
  }

}
