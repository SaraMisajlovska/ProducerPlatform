package mk.ukim.finki.thesis.spi.mapper.impl;

import ecommerce.ProductInfo;
import ecommerce.ProductView;
import mk.ukim.finki.thesis.spi.mapper.AvroObjectMapper;
import mk.ukim.finki.thesis.spi.model.Product;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class AvroObjectMapperImpl implements AvroObjectMapper {

  @Override
  public ProductView mapToProductView(Product product) {

    ProductInfo.Builder productInfoBuilder = ProductInfo.newBuilder();


    productInfoBuilder.setProductId(product.getId());
    productInfoBuilder.setName(product.getName());
    productInfoBuilder.setCategory(product.getCategory());
    productInfoBuilder.setPrice(productInfoBuilder.getPrice());

    ProductView.Builder productViewBuilder = ProductView.newBuilder();
    long timestamp = ZonedDateTime.of(LocalDateTime.now(), ZoneId.systemDefault())
            .toInstant()
            .toEpochMilli();

    productViewBuilder.setTimestamp(timestamp);
    productViewBuilder.setProduct(productInfoBuilder.build());
    productViewBuilder.setUserId("someId");

    return productViewBuilder.build();
  }
}
