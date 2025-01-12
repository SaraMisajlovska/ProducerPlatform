package mk.ukim.finki.thesis.spi.model;

import lombok.Builder;
import lombok.Getter;
import mk.ukim.finki.thesis.common.enums.CancellationReason;

import java.util.List;

@Builder
@Getter
public class CartActivity implements UserActivity {

  private Long cartId;
  private Long userId;
  private List<Product> products;
  private CancellationReason cancellationReason;
}
