package mk.ukim.finki.thesis.kafka.service.impl;


import ecommerce.ProductView;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.thesis.kafka.factory.KafkaTemplateFactory;
import mk.ukim.finki.thesis.kafka.service.KafkaProducerService;
import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static java.util.stream.Collectors.toList;

/**
 * Implementation of {@link KafkaProducerService}.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerServiceImpl<T extends SpecificRecord> implements KafkaProducerService<T> {

  private final
  Map<Class<T>, KafkaTemplateFactory<T>> kafkaTemplateFactories;

  @Value("kafka.topic.producer-one.user-actions")
  String currentProducersOwnTopic;


  @Override
  public void produceMessage(T specificRecord) {

    log.info("Kafka Producer: Producing message to kafka topic");

    KafkaTemplateFactory<T> factory = kafkaTemplateFactories.get(specificRecord.getClass());

    if (factory == null) {
      throw new IllegalArgumentException("No KafkaTemplateFactory found for record type: "
              + specificRecord.getClass().getName());
    }

    KafkaTemplate<String, T> kafkaTemplate = factory.getKafkaTemplate();

    CompletableFuture<SendResult<String, T>> future = kafkaTemplate.send(currentProducersOwnTopic, specificRecord);

    future.whenComplete(this::handleCompletion);
  }

  private <V extends org.apache.avro.specific.SpecificRecord> void handleCompletion(SendResult<String, V> result,
                                                                                    Throwable ex) {

    if (result != null) {
      log.info("Producer record {} successfully sent to topic {}", result.getProducerRecord(),
              currentProducersOwnTopic);
    } else {
      handleException(ex);
    }
  }

  private <V extends org.apache.avro.specific.SpecificRecord> void handleException(Throwable ex) {

    ProducerRecord<String, V> failedRecord = ((KafkaProducerException) ex).getFailedProducerRecord();
    log.error("Failed to send message record {} to topic {}", failedRecord, currentProducersOwnTopic);
  }
}
