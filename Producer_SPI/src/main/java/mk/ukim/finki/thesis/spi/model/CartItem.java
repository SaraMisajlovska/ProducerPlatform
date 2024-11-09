package mk.ukim.finki.thesis.spi.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CartItem implements UserActivity {

  private Long userId;
  private Product product;
  private Integer quantity;
}
