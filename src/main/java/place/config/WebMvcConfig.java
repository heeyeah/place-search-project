package place.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**").allowedOrigins("*").allowedMethods("*").allowedHeaders("*").allowCredentials(true);

        WebMvcConfigurer.super.addCorsMappings(registry);
    }

//    @Autowired
//    public CustomInterceptor customInterceptor() {
//        return new CustomInterceptor();
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//
//        registry
//                .addInterceptor(customInterceptor())
//                .addPathPatterns(Stream.of("/history", "/statistics", "/search").collect(Collectors.toList()));
//    }
}
