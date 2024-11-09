package mk.ukim.finki.thesis.producer.dtos;

import lombok.Getter;

import java.util.List;

@Getter
public class OrderCancellationDTO {
  Long cartId;
  Long userId;
  List<Long> productIds;
}
