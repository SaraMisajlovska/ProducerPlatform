package mk.ukim.finki.thesis.kafka.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.thesis.common.enums.MessageKey;
import org.apache.avro.specific.SpecificRecord;
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
public class ProducerService {

  @Value("${kafka.topic.producer-one.user-actions}")
  String currentProducersOwnTopic;

  private final KafkaTemplate<MessageKey, SpecificRecord> kafkaTemplate;

  public void produceMessage(MessageKey key, SpecificRecord specificRecord) {

    log.info("Producing record: {}", specificRecord);

    CompletableFuture<? extends SendResult<MessageKey, SpecificRecord>> future =
            kafkaTemplate.send(currentProducersOwnTopic, key, specificRecord);

    future.whenComplete(this::handleCompletion);
  }

  public  <V extends org.apache.avro.specific.SpecificRecord> void handleCompletion(SendResult<MessageKey, V> result,
                                                                                    Throwable ex) {

    if (result != null) {
      log.info("Producer record {} successfully sent to topic {}", result.getProducerRecord(),
              currentProducersOwnTopic);
    } else {
      handleException(ex);
    }
  }

  public  <V extends org.apache.avro.specific.SpecificRecord> void handleException(Throwable ex) {

    ProducerRecord<MessageKey, V> failedRecord = ((KafkaProducerException) ex).getFailedProducerRecord();
    log.error("Failed to send message record {} to topic {}", failedRecord, currentProducersOwnTopic);
  }
}
