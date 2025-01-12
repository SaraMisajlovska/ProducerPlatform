package mk.ukim.finki.thesis.producer.mapper;

import mk.ukim.finki.thesis.producer.dtos.CartItemDTO;
import mk.ukim.finki.thesis.producer.dtos.OrderCancellationDTO;
import mk.ukim.finki.thesis.producer.dtos.ProductDTO;
import mk.ukim.finki.thesis.spi.model.CartActivity;
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

  public CartActivity mapToCart(OrderCancellationDTO orderCancellationDTO) {

    List<Product> productList = new ArrayList<>();

    orderCancellationDTO.getProducts().stream().map(this::mapToProduct).forEach(productList::add);

    return CartActivity.builder()
            .cartId(orderCancellationDTO.getCartId())
            .products(productList)
            .cancellationReason(orderCancellationDTO.getReason())
            .build();
  }
}
