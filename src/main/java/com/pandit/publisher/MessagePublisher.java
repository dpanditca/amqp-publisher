package com.pandit.publisher;

import com.pandit.config.AMQPConfig;
import com.pandit.model.CustomMessage;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MessagePublisher {

    public static final String INSTANT_ROUTING_KEY = "device.new";
    public static final String DELAYED_ROUTING_KEY = "check.new";

    @NonNull
    private final AMQPConfig amqpConfig;

    @NonNull
    private final RabbitTemplate rabbitTemplate;

    public void publishMessage(CustomMessage customMessage, Integer delay) {
        if (customMessage == null) {
            throw new NullPointerException("Request body cannot be null");
        }

        if (delay != null) {
            rabbitTemplate.convertAndSend(amqpConfig.getExchange(), DELAYED_ROUTING_KEY, customMessage, message -> {
                message.getMessageProperties().setDelay(delay);
                return message;
            });
            log.info("delayed message to \"{}\": {} at: {}", DELAYED_ROUTING_KEY, customMessage, LocalTime.now());
        } else {
            rabbitTemplate.convertAndSend(amqpConfig.getExchange(), INSTANT_ROUTING_KEY, customMessage, message -> message);
            log.info("published message to \"{}\": {}", INSTANT_ROUTING_KEY, customMessage);
        }
    }

}
