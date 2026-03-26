package se.lexicon.todo_app.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.Arrays;

@Configuration
@ComponentScan("se.lexicon.todo_app.*")
// To use NotifyUtil in your project, uncomment the line below to import the configuration from notify-util-spring module
//@Import(NotifyUtilConfig.class)
public class AppConfig {

    /**
     * Configures a MappingJackson2HttpMessageConverter to support both application/json
     * and application/octet-stream media types.
     * This is necessary because Swagger UI may send the JSON part of a multipart request
     * with a Content-Type of 'application/octet-stream' instead of 'application/json'.
     */
    @Bean
    public MappingJackson2HttpMessageConverter octetStreamJsonConverter(ObjectMapper objectMapper) {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(objectMapper);
        converter.setSupportedMediaTypes(Arrays.asList(
                MediaType.APPLICATION_JSON,
                new MediaType("application", "octet-stream")
        ));
        return converter;
    }
}