package mk.ukim.finki.thesis.spi.handler;

import ecommerce.ProductView;
import lombok.RequiredArgsConstructor;
import mk.ukim.finki.thesis.kafka.service.ProducerService;
import mk.ukim.finki.thesis.spi.mapper.impl.AvroObjectMapperImpl;
import mk.ukim.finki.thesis.spi.model.Product;
import mk.ukim.finki.thesis.spi.model.UserActivity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductViewActivityHandler implements UserActivityHandler {

  private final AvroObjectMapperImpl avroObjectMapper;
  private final ProducerService producerService;

  @Override
  public boolean handles(UserActivity activity) {
    return Product.class.isAssignableFrom(activity.getClass());
  }

  @Override
  public void handle(UserActivity activity) {

    // map activity to avro object
    Product product = (Product) activity;
    ProductView productView = avroObjectMapper.mapToProductView(product);

    // send kafka message
    producerService.produceMessage(productView);
  }
}
