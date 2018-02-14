/**
 * 
 */
package crawler;

import java.util.List;
import java.util.regex.Pattern;
import com.google.common.collect.Lists;

/**
 * @author tiago.gashu
 *
 */
public class Crawler {

  private String url;
  // TODO: precisamos guardar o responseText
  // Talvez guardar em uma classe que de para navegar em dom?
  private String content;
  private Pattern filter;

  public Crawler(String url, Pattern filter) {
    this.url = url;
    this.filter = filter;
  }

  //

  public void crawl(String url) {
    // TODO
  }

  public boolean find(String word) {
    // TODO
    return false;
  }

  public List<String> getLinks() {
    // TODO
    return Lists.newArrayList();
  }

}
