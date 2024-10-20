package mk.ukim.finki.thesis.producer.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
  private String id;
  private String name;
  private String description;
  private double price;
  private int stock;
  private String category;
}
