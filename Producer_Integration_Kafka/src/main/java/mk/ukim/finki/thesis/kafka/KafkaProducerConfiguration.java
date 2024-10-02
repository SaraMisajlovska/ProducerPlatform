package mk.ukim.finki.thesis.kafka;

import io.confluent.kafka.serializers.KafkaAvroSerializer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.thesis.kafka.util.KafkaProps;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.ssl.DefaultSslBundleRegistry;
import org.springframework.boot.ssl.SslBundles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

import static org.apache.kafka.clients.CommonClientConfigs.SECURITY_PROTOCOL_CONFIG;

// TODO smisajlo 15.7.24: Explain the annotations of this class
@Slf4j
@EnableKafka
@Configuration
@RequiredArgsConstructor
public class KafkaProducerConfiguration {

  private final KafkaProperties kafkaProperties;

  @Value("${truststore.path}")
  private String TRUSTSTORE_PATH;

  @Value("${truststore.password}")
  private String TRUSTSTORE_PASSWORD;

  @Value("${keystore.path}")
  private String KEYSTORE_PATH;

  @Value("${keystore.password}")
  private String KEYSTORE_PASSWORD;

  @Value("${kafka.schema-registry.url}")
  private String SCHEMA_REGISTRY_URL;

  /**
   * Producer configuration properties.
   */
  @Bean
  public Map<String, Object> producerConfigs() {
    SslBundles sslBundles = new DefaultSslBundleRegistry();
    Map<String, Object> props = new HashMap<>(kafkaProperties.buildProducerProperties(sslBundles));

    props.put("key.serializer", StringSerializer.class.getName());
    props.put("value.serializer", KafkaAvroSerializer.class.getName());

    //kafka client configuration
    props.put(SECURITY_PROTOCOL_CONFIG, "SSL");
    props.put(SslConfigs.SSL_KEY_PASSWORD_CONFIG, KEYSTORE_PASSWORD);
    props.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, TRUSTSTORE_PATH);
    props.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, TRUSTSTORE_PASSWORD);
    props.put(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG, KEYSTORE_PATH);
    props.put(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG, KEYSTORE_PASSWORD);

    // schema registry config
    props.put("schema.registry.url", SCHEMA_REGISTRY_URL);

    props.put(KafkaProps.SCHEMA_REGISTRY_SSL_KEYSTORE_LOCATION, KEYSTORE_PATH);
    props.put(KafkaProps.SCHEMA_REGISTRY_SSL_KEYSTORE_PASSWORD, KEYSTORE_PASSWORD);
    props.put(KafkaProps.SCHEMA_REGISTRY_SSL_KEY_PASSWORD, KEYSTORE_PASSWORD);
    props.put(KafkaProps.SCHEMA_REGISTRY_SSL_TRUSTSTORE_LOCATION, TRUSTSTORE_PATH);
    props.put(KafkaProps.SCHEMA_REGISTRY_SSL_TRUSTSTORE_PASSWORD, TRUSTSTORE_PASSWORD);

    // Prevents uploading of the schema to Schema registry automatically
    props.put("schema.registry.auto.register.schemas", "false");
    // Prevents uploading of the schema to Kafka automatically
    props.put("auto.register.schemas", "false");
    props.put("use.latest.version", "true");
    // Prevents automatic topic creation to Kafka automatically
    props.put("topic.creation.enable", "false");
    props.put("enable.idempotence", "false");

    log.info("Kafka Producer configuration properties: {}", props);

    return props;
  }

  /**
   * Producer Factory for creating kafka producer instances.
   */
  @Bean
  public <T extends org.apache.avro.specific.SpecificRecord> ProducerFactory<String, T> producerFactory() {
    return new DefaultKafkaProducerFactory<>(producerConfigs());
  }

  /**
   * Producer wrapper template for sending data to kafka topics.
   */
  @Bean
  public <T extends org.apache.avro.specific.SpecificRecord> KafkaTemplate<String, T> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
  }
}
