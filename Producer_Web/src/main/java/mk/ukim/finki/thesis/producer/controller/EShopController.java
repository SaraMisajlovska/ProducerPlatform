package mk.ukim.finki.thesis.producer.controller;

import mk.ukim.finki.thesis.producer.config.TrackActivity;
import mk.ukim.finki.thesis.producer.dtos.CartItemDTO;
import mk.ukim.finki.thesis.producer.dtos.OrderCancellationDTO;
import mk.ukim.finki.thesis.producer.dtos.ProductDTO;
import mk.ukim.finki.thesis.producer.dtos.SearchResultDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EShopController {

  //Apply after returning advice
  @TrackActivity
  @GetMapping("/product/{productId}")
  public ResponseEntity<ProductDTO> viewProduct(@PathVariable String productId) {

    ProductDTO product = new ProductDTO();
    product.setId(productId);
    product.setName("Example Product");
    product.setDescription("This is an example product.");
    product.setPrice(99.99);
    product.setStock(10);

    return ResponseEntity.ok(product);
  }

  @PostMapping("/cart")
  public ResponseEntity<CartItemDTO> addProductToCart(@RequestBody CartItemDTO cartItem) {
    // Simulate adding to cart
    cartItem.setCartId("exampleCartId");
    return ResponseEntity.ok(cartItem);
  }

  @PostMapping("/order/cancel")
  public ResponseEntity<OrderCancellationDTO> cancelOrder(@RequestBody OrderCancellationDTO orderCancellation) {
    // Simulate order cancellation
    return ResponseEntity.ok(orderCancellation);
  }

  @GetMapping("/search")
  public ResponseEntity<SearchResultDTO> searchProducts(@RequestParam String query) {
    // Example search result
    List<ProductDTO> products = new ArrayList<>();
    ProductDTO product = new ProductDTO();
    product.setId("1");
    product.setName("Example Product 1");
    product.setDescription("This is an example product 1.");
    product.setPrice(19.99);
    product.setStock(50);
    products.add(product);

    SearchResultDTO searchResult = new SearchResultDTO();
    products.forEach(searchResult::addProductToResult);

    return ResponseEntity.ok(searchResult);
  }
}
