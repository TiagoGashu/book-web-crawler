/**
 * 
 */
package books.json;

/**
 * @author tiago.gashu
 *
 */
public class MangaJson extends BookJson {

  /** */
  private static final long serialVersionUID = -6082380207078361379L;

  private boolean completed;


  public boolean isCompleted() {
    return completed;
  }

  public void setCompleted(boolean completed) {
    this.completed = completed;
  }

}
