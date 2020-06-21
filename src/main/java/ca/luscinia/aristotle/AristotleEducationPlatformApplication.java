package ca.luscinia.aristotle;

import ca.luscinia.aristotle.config.AristotleConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AristotleConfigProperties.class)
public class AristotleEducationPlatformApplication {

    private static AristotleConfigProperties aristotleConfigProperties;

    public static void main(String[] args) {
        SpringApplication.run(AristotleEducationPlatformApplication.class, args);
    }

}
