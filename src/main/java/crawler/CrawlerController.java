/**
 * 
 */
package crawler;

import java.util.List;
import java.util.Set;
import org.springframework.util.CollectionUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * @author tiago.gashu
 */
public class CrawlerController {

  private static final int MAX_PAGES_TO_SEARCH = 50;
  private Set<String> pagesVisited = Sets.newHashSet();
  private List<String> pagesToVisit = Lists.newArrayList();

  private String nextPageUrl() {
    if (CollectionUtils.isEmpty(pagesToVisit)) {
      return null;
    }

    String url = this.pagesToVisit.remove(0);
    pagesVisited.add(url);
    return url;
  }

}
