package mk.ukim.finki.thesis.kafka.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class KafkaTopicConfig {

  @Value("${kafka.topic.producer-one.user-actions}")
  private String topicName;

  @Bean
  public NewTopic createTopic(){
    return new NewTopic(topicName, 1, (short) 1);
  }
}
