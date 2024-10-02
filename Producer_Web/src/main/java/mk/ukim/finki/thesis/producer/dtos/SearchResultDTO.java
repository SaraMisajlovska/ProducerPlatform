package mk.ukim.finki.thesis.producer.dtos;

import lombok.Getter;

import java.util.List;

@Getter
public class SearchResultDTO {
  private List<ProductDTO> results;

  public void addProductToResult(ProductDTO productDTO) {
    this.results.add(productDTO);
  }
}
