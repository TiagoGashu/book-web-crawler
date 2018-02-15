/**
 * 
 */
package books.json;

import java.io.Serializable;

/**
 * @author tiago.gashu
 */
public class MangaPageJson implements Serializable {

  /** */
  private static final long serialVersionUID = -1734165748040688365L;

  private Long id;
  private Long ordem;
  private byte[] data;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getOrdem() {
    return ordem;
  }

  public void setOrdem(Long ordem) {
    this.ordem = ordem;
  }

  public byte[] getData() {
    return data;
  }

  public void setData(byte[] data) {
    this.data = data;
  }

}
