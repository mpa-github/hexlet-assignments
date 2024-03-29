package exercise;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    // BEGIN
    @Bean
    Queue queue() {
        // Задаём имя очереди, в которую мы будем посылать сообщения.
        return new Queue("queue", false);
    }

    @Bean
    TopicExchange exchange() {
        // Задаём имя "обменника", который помещает сообщение в определённую очередь.
        return new TopicExchange("exchange");
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        // Связывает очередь с разветвителем и определяет поведение при добавлении нового сообщения в exchange.
        // Сообщения с ключом "key" будут с помощью обменника "exchange" направлены в очередь "queue".
        return BindingBuilder.bind(queue).to(exchange).with("key");
    }
    // END
}
