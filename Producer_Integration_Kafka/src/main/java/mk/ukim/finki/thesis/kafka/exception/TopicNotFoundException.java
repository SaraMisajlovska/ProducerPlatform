package mk.ukim.finki.thesis.kafka.exception;

/**
 * Exception thrown when the topic was not found.
 */
public class TopicNotFoundException extends RuntimeException {

  public TopicNotFoundException(String err) {
    super(err);
  }
}
