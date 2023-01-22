package pl.krzysztofwywial.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "path")
@ConstructorBinding
@RequiredArgsConstructor
@Getter
public class ImageConfig {
    private final String image;
}
