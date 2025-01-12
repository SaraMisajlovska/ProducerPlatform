package mk.ukim.finki.thesis.producer.aspect;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.thesis.spi.handler.UserActivityHandler;
import mk.ukim.finki.thesis.spi.model.UserActivity;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.lang.String.format;

@Aspect
@Component
@RequiredArgsConstructor
public class TrackActivityAspect {

  private final List<UserActivityHandler> userActivityHandlers;

  @AfterReturning(pointcut = "methodsAnnotatedWithTrackActivity()", returning = "responseEntity")
  public void afterReturningAdvice(ResponseEntity<? extends UserActivity> responseEntity) {

    UserActivity activity = responseEntity.getBody();
    String errorMessage = format("No handler found for %s", activity);

    userActivityHandlers.stream()
            .filter(handler -> handler.handles(activity))
            .findFirst()
            .orElseThrow(() -> new RuntimeException(errorMessage))
            .handle(activity);
  }

  @Pointcut("@annotation(mk.ukim.finki.thesis.producer.config.TrackActivity)")
  public void methodsAnnotatedWithTrackActivity() {

  }
}
