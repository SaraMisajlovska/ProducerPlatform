package mk.ukim.finki.thesis.kafka.factory.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.thesis.kafka.factory.KafkaTemplateFactory;
import org.apache.avro.specific.SpecificRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaTemplateFactoryImpl<T extends SpecificRecord> implements KafkaTemplateFactory<T> {

  private final ProducerFactory<String, T> producerFactory;

  @Override
  public KafkaTemplate<String, T> getKafkaTemplate() {
    return new KafkaTemplate<>(producerFactory);
  }
}
