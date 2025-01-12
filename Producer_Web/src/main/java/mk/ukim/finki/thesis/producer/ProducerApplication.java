package mk.ukim.finki.thesis.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "mk.ukim.finki.thesis.producer",
        "mk.ukim.finki.thesis.spi",
        "mk.ukim.finki.thesis.common",
        "mk.ukim.finki.thesis.kafka"})
public class ProducerApplication {

  public static void main(String[] args) {
    SpringApplication.run(ProducerApplication.class, args);
  }

}