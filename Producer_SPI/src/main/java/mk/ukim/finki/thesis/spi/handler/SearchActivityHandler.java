package mk.ukim.finki.thesis.spi.handler;

import ecommerce.SearchQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.thesis.kafka.service.ProducerService;
import mk.ukim.finki.thesis.spi.mapper.AvroObjectMapper;
import mk.ukim.finki.thesis.spi.model.Search;
import mk.ukim.finki.thesis.spi.model.UserActivity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchActivityHandler implements UserActivityHandler {

  private final AvroObjectMapper avroObjectMapper;
  private final ProducerService producerService;

  @Override
  public boolean handles(UserActivity activity) {
    return Search.class.isAssignableFrom(activity.getClass());
  }

  @Override
  public void handle(UserActivity activity) {
    Search search = (Search) activity;

    SearchQuery searchQuery = avroObjectMapper.mapToSearchQuery(search);

    producerService.produceMessage(searchQuery);
  }
}
