package mk.ukim.finki.thesis.producer.config;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
// TODO smisajlo 12.10.24: add javadoc
@Retention(RUNTIME)
@Target(METHOD)
public @interface TrackActivity {
}
