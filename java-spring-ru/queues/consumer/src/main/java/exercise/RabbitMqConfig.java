package exercise;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    // BEGIN
    @Bean
    Queue queue() {
        // Задаём имя очереди, из которой мы будем доставать сообщения.
        return new Queue("queue", false);
    }
    // END
}
