package mk.ukim.finki.thesis.kafka.service;

import org.apache.avro.specific.SpecificRecord;

/**
 * Kafka Producer Service.
 */
public interface KafkaProducerService<T extends SpecificRecord> {

  /**
   * Produces a user activity to a kafka topic.
   */
  void produceMessage(T userActivity);
}
