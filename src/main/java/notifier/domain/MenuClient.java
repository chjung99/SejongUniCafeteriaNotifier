package notifier.domain;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

public class MenuClient {

    private final String BASE_URL = "http://localhost:8080/api/menu";

    private final RestTemplate restTemplate;

    public MenuClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<Menu> requestMenu(String date, String mealTime) {
        String requestUrl = BASE_URL + "?date=" + date + "&" + "mealTime=" + mealTime;
        ResponseEntity<Menu> responseEntity = restTemplate.getForEntity(requestUrl, Menu.class);
        return Optional.ofNullable(responseEntity.getBody());
    }
}
