package mk.ukim.finki.orders.xport.client;

import mk.ukim.finki.orders.domain.valueobjects.Games;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

@Service
public class GameClient {
    private final RestTemplate restTemplate;
    private final String serverUrl;

    public GameClient(@Value("${app.games.url}") String serverUrl) {
        this.serverUrl = serverUrl;
        this.restTemplate = new RestTemplate();
        var request = new SimpleClientHttpRequestFactory();
        this.restTemplate.setRequestFactory(request);
    }
    private UriComponentsBuilder uri() {
        return UriComponentsBuilder.fromUriString(this.serverUrl);
    }

    public List<Games> findAll() {
        try{
            return restTemplate.exchange(uri().path("/api/games").build().toUri(), HttpMethod.GET, null, new ParameterizedTypeReference<List<Games>>() {
            }).getBody();
        }catch (Exception e){
            return Collections.emptyList();
        }
    }
}
