package chechi.nino.bootcamp.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfiguration {

    @Value("${EMAIL_USERNAME}")
    private String fromEmail;

    @Value("${EMAIL_PASSWORD}")
    private String password;

    @Bean
    public String fromEmail() {
        return fromEmail;
    }

    @Bean
    public String emailPassword() {
        return password;
    }
}