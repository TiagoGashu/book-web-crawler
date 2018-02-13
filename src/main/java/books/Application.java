package books;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.PathResourceResolver;

@SpringBootApplication
@ComponentScan(basePackages = {"books.*", "generics.*"})
public class Application extends WebMvcConfigurerAdapter {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    PathResourceResolver pathResourceResolver = new PathResourceResolver();
    registry.addResourceHandler("/resources/**").addResourceLocations("/static/")
        .setCachePeriod(3600).resourceChain(true).addResolver(pathResourceResolver);
  }

}
