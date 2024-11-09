package mk.ukim.finki.thesis.spi.handler;

import ecommerce.CheckoutAbandoned;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.thesis.kafka.service.ProducerService;
import mk.ukim.finki.thesis.spi.mapper.AvroObjectMapper;
import mk.ukim.finki.thesis.spi.model.Cart;
import mk.ukim.finki.thesis.spi.model.UserActivity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CheckoutAbandonedActivityHandler implements UserActivityHandler {

  private final AvroObjectMapper avroObjectMapper;
  private final ProducerService producerService;

  @Override
  public boolean handles(UserActivity activity) {
    return Cart.class.isAssignableFrom(activity.getClass());
  }

  @Override
  public void handle(UserActivity activity) {

    Cart cart = (Cart) activity;

    CheckoutAbandoned checkoutAbandoned = avroObjectMapper.mapToCheckoutAbandoned(cart);

    producerService.produceMessage(checkoutAbandoned);
  }
}
