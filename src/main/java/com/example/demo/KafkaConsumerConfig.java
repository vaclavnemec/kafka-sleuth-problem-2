package com.example.demo;

import lombok.AllArgsConstructor;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer2;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@EnableKafka
@Configuration
@AllArgsConstructor
public class KafkaConsumerConfig {

	private final KafkaProperties kafkaProperties;

	@Bean
	public ConsumerFactory<String, MyTestMessage> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(kafkaProperties.buildConsumerProperties(), new StringDeserializer(),
				new ErrorHandlingDeserializer2<>(new JsonDeserializer<>(MyTestMessage.class)));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, MyTestMessage> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, MyTestMessage> factory =
				new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}
}
