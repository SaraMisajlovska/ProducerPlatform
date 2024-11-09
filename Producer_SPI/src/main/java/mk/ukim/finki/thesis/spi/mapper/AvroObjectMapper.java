package mk.ukim.finki.thesis.spi.mapper;

import ecommerce.AddToCart;
import ecommerce.CheckoutAbandoned;
import ecommerce.ProductView;
import ecommerce.SearchQuery;
import mk.ukim.finki.thesis.spi.model.Cart;
import mk.ukim.finki.thesis.spi.model.CartItem;
import mk.ukim.finki.thesis.spi.model.Product;
import mk.ukim.finki.thesis.spi.model.Search;

public interface AvroObjectMapper {

  ProductView mapToProductView(Product product);

  AddToCart mapToAddToCart(CartItem product);

  SearchQuery mapToSearchQuery(Search search);

  CheckoutAbandoned mapToCheckoutAbandoned(Cart cart);
}
