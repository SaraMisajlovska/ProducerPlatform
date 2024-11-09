package mk.ukim.finki.thesis.producer.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDTO {
  private Long cartId;
  private Long productId;
  private int quantity;
}
