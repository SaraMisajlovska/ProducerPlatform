package mk.ukim.finki.thesis.producer.dtos;

import lombok.Getter;
import mk.ukim.finki.thesis.common.enums.CancellationReason;

import java.util.List;

@Getter
public class OrderCancellationDTO {
  Long cartId;
  List<ProductDTO> products;
  CancellationReason reason;
}
