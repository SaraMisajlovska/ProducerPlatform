package mk.ukim.finki.thesis.spi.handler;

import ecommerce.AddToCart;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.thesis.kafka.service.ProducerService;
import mk.ukim.finki.thesis.spi.mapper.AvroObjectMapper;
import mk.ukim.finki.thesis.spi.model.CartItem;
import mk.ukim.finki.thesis.spi.model.UserActivity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddToCartActivityHandler implements UserActivityHandler {

  private final AvroObjectMapper avroObjectMapper;
  private final ProducerService producerService;

  @Override
  public boolean handles(UserActivity activity) {
    return CartItem.class.isAssignableFrom(activity.getClass());
  }

  @Override
  public void handle(UserActivity activity) {

    // map activity to avro object
    CartItem cartItem = (CartItem) activity;
    AddToCart addToCart = avroObjectMapper.mapToAddToCart(cartItem);

    // send kafka message
    producerService.produceMessage(addToCart);
  }
}
