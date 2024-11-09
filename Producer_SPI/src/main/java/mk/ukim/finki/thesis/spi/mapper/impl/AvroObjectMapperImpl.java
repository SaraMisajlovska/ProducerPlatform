package mk.ukim.finki.thesis.spi.mapper.impl;

import ecommerce.*;
import mk.ukim.finki.thesis.spi.mapper.AvroObjectMapper;
import mk.ukim.finki.thesis.spi.model.Cart;
import mk.ukim.finki.thesis.spi.model.CartItem;
import mk.ukim.finki.thesis.spi.model.Product;
import mk.ukim.finki.thesis.spi.model.Search;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class AvroObjectMapperImpl implements AvroObjectMapper {

  private static long getTimestamp() {
    return ZonedDateTime.of(LocalDateTime.now(), ZoneId.systemDefault())
            .toInstant()
            .toEpochMilli();
  }

  private ProductInfo mapToProductInfo(Product product) {

    ProductInfo.Builder productInfoBuilder = ProductInfo.newBuilder();

    productInfoBuilder.setProductId(product.getId());
    productInfoBuilder.setName(product.getName());
    productInfoBuilder.setCategory(product.getCategory());
    productInfoBuilder.setPrice(productInfoBuilder.getPrice());

    return productInfoBuilder.build();
  }

  @Override
  public ProductView mapToProductView(Product product) {

    ProductInfo productInfo = mapToProductInfo(product);

    ProductView.Builder productViewBuilder = ProductView.newBuilder();
    productViewBuilder.setTimestamp(getTimestamp());
    productViewBuilder.setProduct(productInfo);
    productViewBuilder.setUserId(1234L);

    return productViewBuilder.build();
  }

  @Override
  public AddToCart mapToAddToCart(CartItem cartItem) {

    ProductInfo productInfo = mapToProductInfo(cartItem.getProduct());
    AddToCart.Builder addToCartBuilder = AddToCart.newBuilder();

    addToCartBuilder.setProduct(productInfo);
    addToCartBuilder.setQuantity(cartItem.getQuantity());
    addToCartBuilder.setUserId(cartItem.getUserId());
    addToCartBuilder.setTimestamp(getTimestamp());

    return addToCartBuilder.build();
  }

  @Override
  public SearchQuery mapToSearchQuery(Search search) {

    SearchQuery.Builder searchQueryBuilder = SearchQuery.newBuilder();

    searchQueryBuilder.setQuery(search.getQuery());
    searchQueryBuilder.setUserId(search.getUserId());
    searchQueryBuilder.setTimestamp(getTimestamp());

    return searchQueryBuilder.build();
  }

  @Override
  public CheckoutAbandoned mapToCheckoutAbandoned(Cart cart) {
    List<ProductInfo> productInfos = cart.getProducts()
            .stream()
            .map(this::mapToProductInfo)
            .toList();

    CheckoutAbandoned.Builder checkoutAbandonedBuilder = CheckoutAbandoned.newBuilder();

    checkoutAbandonedBuilder.setCartId(cart.getCartId());
    checkoutAbandonedBuilder.setUserId(cart.getUserId());
    checkoutAbandonedBuilder.setProducts(productInfos);
    checkoutAbandonedBuilder.setTimestamp(getTimestamp());

    return checkoutAbandonedBuilder.build();
  }
}
