package mk.ukim.finki.thesis.producer.mapper;

import mk.ukim.finki.thesis.producer.dtos.CartItemDTO;
import mk.ukim.finki.thesis.producer.dtos.OrderCancellationDTO;
import mk.ukim.finki.thesis.producer.dtos.ProductDTO;
import mk.ukim.finki.thesis.spi.model.Cart;
import mk.ukim.finki.thesis.spi.model.CartItem;
import mk.ukim.finki.thesis.spi.model.Product;
import mk.ukim.finki.thesis.spi.model.Search;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DTOMapper {

  public Product mapToProduct(ProductDTO productDTO) {
    return Product.builder()
            .id(productDTO.getId())
            .name(productDTO.getName())
            .price(productDTO.getPrice())
            .category(productDTO.getCategory())
            .build();
  }

  public Product mapToProductById(Long productId) {
    //fetch form db
    return Product.builder()
            .id(productId)
            .name("some name")
            .price(10000)
            .category("some category")
            .build();
  }

  public CartItem mapToCartItem(CartItemDTO cartItemDTO) {

    Product persitedProduct = mapToProductById(cartItemDTO.getProductId());

    return CartItem.builder()
            //this id needs to be taken from the cart object -> user
            .userId(1234L)
            .quantity(cartItemDTO.getQuantity())
            .product(persitedProduct)
            .cartId(cartItemDTO.getCartId())
            .build();
  }

  public Search mapToSearchItem(String query, Long userId) {
    return Search.builder()
            .query(query)
            .userId(userId)
            .build();
  }

  public Cart mapToCart(OrderCancellationDTO orderCancellationDTO) {

    List<Product> productList = new ArrayList<>();

    orderCancellationDTO.getProductIds().stream().map(this::mapToProductById).forEach(productList::add);


    return Cart.builder()
            .cartId(orderCancellationDTO.getCartId())
            .userId(orderCancellationDTO.getUserId())
            .products(productList)
            .build();
  }
}
