package pl.krzysztofwywial;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class CarRentalKwApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarRentalKwApplication.class, args);
    }

}
