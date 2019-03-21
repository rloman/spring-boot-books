package nl.yacht.books.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBeans {

    @Bean
    public Person anne() {
        return new Person("Anne");
    }

    @Bean
    public Person will() {
        return new Person("Will");
    }
}
