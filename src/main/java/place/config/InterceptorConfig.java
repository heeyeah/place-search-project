package place.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import place.CustomInterceptor;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    public CustomInterceptor customInterceptor() {
        return new CustomInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry
                .addInterceptor(customInterceptor())
                .addPathPatterns(Stream.of("/history", "/statistics", "/search").collect(Collectors.toList()));
    }
}
