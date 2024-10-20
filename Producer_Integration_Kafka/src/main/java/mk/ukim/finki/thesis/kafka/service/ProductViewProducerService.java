package mk.ukim.finki.thesis.kafka.service;


import ecommerce.ProductView;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductViewProducerService {

  private final KafkaTemplate<String, ProductView> kafkaTemplate;

  @Value("${kafka.topic.producer-one.user-actions}")
  String currentProducersOwnTopic;

  public void produceMessage(ProductView productView) {

    log.info("Kafka Producer: Producing message to kafka topic");

    CompletableFuture<? extends SendResult<String, ProductView>> future =
            kafkaTemplate.send(currentProducersOwnTopic, productView);

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
