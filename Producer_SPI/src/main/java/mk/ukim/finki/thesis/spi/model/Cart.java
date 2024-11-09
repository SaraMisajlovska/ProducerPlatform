package mk.ukim.finki.thesis.spi.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class Cart implements UserActivity {

  private Long cartId;
  private Long userId;
  private List<Product> products;
}
