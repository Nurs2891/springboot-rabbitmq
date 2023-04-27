package net.javaguides.springbootrabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.name}")
    private String queue;

    @Value("${rabbitmq.queue.json.name}")
    private String jsonQueue;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    @Value("${rabbitmq.routing.json.key}")
    private String routingJsonKey;


    // spring bean for rabbit queue
    @Bean
    public Queue queue(){
        return new Queue(queue);
    }

    // spring bean for queue (store json message)
    @Bean
    public Queue jsonQueue(){
        return new Queue(jsonQueue);
    }

    // spring bean for rabbit exchange
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange);
    }

    //Для привязки queue and exchange с помощью ключа routing key
    @Bean
    public Binding binding(){
        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with(routingKey);
    }

    //Для привязки json queue and exchange с помощью ключа routing key
    @Bean
    public Binding jsonBinding(){
        return BindingBuilder
                .bind(jsonQueue())
                .to(exchange())
                .with(routingJsonKey);
    }

    @Bean//json
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean//Настроили. Теперь шаблон будет поддерживать сообщение в формате json
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

//    Spring сам их настроит
//    ConnectionFactory
//    RabbitTemplate
//    RabbitAdmin
}
