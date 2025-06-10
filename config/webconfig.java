@Configuration
public class WebConfig implements WebMvcConfigurer {
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry
      .addResourceHandler("/uploads/**")
      .addResourceLocations("classpath:/static/uploads/");
  }
}