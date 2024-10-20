package mk.ukim.finki.thesis.spi.handler;

import mk.ukim.finki.thesis.spi.model.UserActivity;

public interface UserActivityHandler {

  boolean handles(UserActivity activity);

  void handle(UserActivity activity);
}
