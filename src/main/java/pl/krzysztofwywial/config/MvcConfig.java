package pl.krzysztofwywial.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        exposeDirectory("images", registry);
    }

    private void exposeDirectory(String directoryName, ResourceHandlerRegistry registry) {
        Path uploadDirectory = Paths.get(directoryName);
        String uploadPath = uploadDirectory.toFile().getAbsolutePath();

        if (directoryName.startsWith("../")) {
            directoryName = directoryName.replace("../", "");
        }
        registry.addResourceHandler("/" + directoryName + "/**")
                .addResourceLocations("file:/" + uploadPath + "/");
    }
}
