/**
 * :P
 */
package books.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author tiago.gashu
 *
 */
@Controller
public class ApplicationController {

  // private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {"classpath:/META-INF/resources/",
  // "classpath:/resources/", "classpath:/static/", "classpath:/public/"};

  @RequestMapping("/")
  public String home() {
    return "index";
  }

}
