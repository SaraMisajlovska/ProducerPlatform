package mk.ukim.finki.thesis.producer.mapper;

import mk.ukim.finki.thesis.producer.dtos.ProductDTO;
import mk.ukim.finki.thesis.spi.model.Product;
import org.springframework.stereotype.Service;

@Service
public class DTOMapper {

   public Product mapToProduct(ProductDTO productDTO){
     return Product.builder()
             .id(productDTO.getId())
             .name(productDTO.getName())
             .price(productDTO.getPrice())
             .category(productDTO.getCategory())
             .build();
   }
}
