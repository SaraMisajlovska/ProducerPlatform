package mk.ukim.finki.thesis.producer.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TrackActivityAspect {


  @AfterReturning(value = "methodsAnnotatedWithTrackActivity()", returning = "returnObject")
  public void afterReturningAdvice(JoinPoint joinPoint, Object returnObject) {
    //find the correct handling service


  }

  @Pointcut("@annotation(mk.ukim.finki.thesis.producer.config.TrackActivity)")
  public void methodsAnnotatedWithTrackActivity() {
  }
}
