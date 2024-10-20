package mk.ukim.finki.thesis.spi.mapper;

import ecommerce.ProductView;
import mk.ukim.finki.thesis.spi.model.Product;

public interface AvroObjectMapper {

  ProductView mapToProductView(Product product);

}
