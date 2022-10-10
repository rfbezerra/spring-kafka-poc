package net.rfbezerra.kafkaproducer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value(value = "${kafka.bootstrapServers}")
    private String bootstrapServers;

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        StringSerializer keySerializer = new StringSerializer();
        StringSerializer valueSerializer = new StringSerializer();

        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);

        return new DefaultKafkaProducerFactory<>(props, keySerializer, valueSerializer);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }


    @Bean
    public NewTopic newTopic() {
        return TopicBuilder.name("net.rfbezerra.messages")
                .partitions(3)
                .replicas(1)
                .build();
    }
}
