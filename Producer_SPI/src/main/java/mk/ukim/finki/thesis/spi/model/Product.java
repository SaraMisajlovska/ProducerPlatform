package mk.ukim.finki.thesis.spi.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Product implements UserActivity {
  private String id;
  private String name;
  private double price;
  private String category;
}
