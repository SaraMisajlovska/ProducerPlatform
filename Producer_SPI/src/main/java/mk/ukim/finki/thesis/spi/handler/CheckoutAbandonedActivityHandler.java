package mk.ukim.finki.thesis.spi.handler;

import ecommerce.CheckoutAbandoned;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.thesis.kafka.service.ProducerService;
import mk.ukim.finki.thesis.spi.mapper.AvroObjectMapper;
import mk.ukim.finki.thesis.spi.model.CartActivity;
import mk.ukim.finki.thesis.spi.model.UserActivity;
import org.springframework.stereotype.Service;

import static mk.ukim.finki.thesis.common.enums.MessageKey.CART_ABANDONED;

@Slf4j
@Service
@RequiredArgsConstructor
public class CheckoutAbandonedActivityHandler implements UserActivityHandler {

  private final AvroObjectMapper avroObjectMapper;
  private final ProducerService producerService;

  @Override
  public boolean handles(UserActivity activity) {
    return CartActivity.class.isAssignableFrom(activity.getClass());
  }

  @Override
  public void handle(UserActivity activity) {

    CartActivity cartActivity = (CartActivity) activity;

    CheckoutAbandoned checkoutAbandoned = avroObjectMapper.mapToCheckoutAbandoned(cartActivity);

    producerService.produceMessage(CART_ABANDONED, checkoutAbandoned);
  }
}
