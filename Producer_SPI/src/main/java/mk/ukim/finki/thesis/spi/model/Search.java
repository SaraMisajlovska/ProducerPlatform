package mk.ukim.finki.thesis.spi.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Search implements UserActivity {

  private Long userId;
  private String query;
}
