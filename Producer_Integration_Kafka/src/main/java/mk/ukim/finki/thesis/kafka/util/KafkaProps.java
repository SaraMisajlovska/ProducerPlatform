package mk.ukim.finki.thesis.kafka.util;

import static org.apache.kafka.common.config.SslConfigs.*;

/**
 * Additional kafka properties.
 */
public final class KafkaProps {

  private static final String SCHEMA_REGISTRY_PREFIX = "schema.registry.";

  /**
   * Schema registry SSL truststore location.
   */
  public static final String SCHEMA_REGISTRY_SSL_TRUSTSTORE_LOCATION = SCHEMA_REGISTRY_PREFIX
          + SSL_TRUSTSTORE_LOCATION_CONFIG;

  /**
   * Schema registry SSL truststore password.
   */
  public static final String SCHEMA_REGISTRY_SSL_TRUSTSTORE_PASSWORD = SCHEMA_REGISTRY_PREFIX
          + SSL_TRUSTSTORE_PASSWORD_CONFIG;

  /**
   * Schema registry SSL keystore location.
   */
  public static final String SCHEMA_REGISTRY_SSL_KEYSTORE_LOCATION = SCHEMA_REGISTRY_PREFIX
          + SSL_KEYSTORE_LOCATION_CONFIG;

  /**
   * Schema registry SSL keystore password.
   */
  public static final String SCHEMA_REGISTRY_SSL_KEYSTORE_PASSWORD = SCHEMA_REGISTRY_PREFIX
          + SSL_KEYSTORE_PASSWORD_CONFIG;

  /**
   * Schema registry SSL key password.
   */
  public static final String SCHEMA_REGISTRY_SSL_KEY_PASSWORD = SCHEMA_REGISTRY_PREFIX + SSL_KEY_PASSWORD_CONFIG;

  private KafkaProps() {
    throw new UnsupportedOperationException("Do not instantiate!");
  }
}