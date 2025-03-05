package mk.ukim.finki.thesis.producer.controller;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.thesis.producer.config.TrackActivity;
import mk.ukim.finki.thesis.producer.dtos.CartItemDTO;
import mk.ukim.finki.thesis.producer.dtos.OrderCancellationDTO;
import mk.ukim.finki.thesis.producer.dtos.ProductDTO;
import mk.ukim.finki.thesis.producer.mapper.DTOMapper;
import mk.ukim.finki.thesis.spi.model.CartActivity;
import mk.ukim.finki.thesis.spi.model.CartItem;
import mk.ukim.finki.thesis.spi.model.Product;
import mk.ukim.finki.thesis.spi.model.Search;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EShopController {

  private final DTOMapper dtoMapper;

  //Apply after returning advice
  @TrackActivity
  @PostMapping("/product/")
  public ResponseEntity<Product> viewProduct(@RequestBody ProductDTO productDTO) {

    Product body = dtoMapper.mapToProduct(productDTO);
    return ResponseEntity.ok(body);
  }

  @TrackActivity
  @PostMapping("/cart")
  public ResponseEntity<CartItem> addProductToCart(@RequestBody CartItemDTO cartItem) {

    CartItem body = dtoMapper.mapToCartItem(cartItem);
    return ResponseEntity.ok(body);
  }

  @TrackActivity
  @PostMapping("/order/cancel")
  public ResponseEntity<CartActivity> cancelOrder(@RequestBody OrderCancellationDTO orderCancellation) {

    CartActivity cartActivity = dtoMapper.mapToCart(orderCancellation);
    return ResponseEntity.ok(cartActivity);
  }

  @TrackActivity
  @GetMapping("/search")
  public ResponseEntity<Search> searchProducts(@RequestParam String query, @RequestParam Long userId) {

    Search search = dtoMapper.mapToSearchItem(query, userId);
    return ResponseEntity.ok(search);
  }
}
