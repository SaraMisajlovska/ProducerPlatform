package mk.ukim.finki.thesis.kafka.factory;

import org.springframework.kafka.core.KafkaTemplate;

public interface KafkaTemplateFactory<T extends org.apache.avro.specific.SpecificRecord> {

  KafkaTemplate<String, T> getKafkaTemplate();
}
