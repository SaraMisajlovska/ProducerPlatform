package mk.ukim.finki.thesis.producer.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDTO {
  private String cartId;
  private String productId;
  private int quantity;
}
