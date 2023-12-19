package ru.itis.employmentstub.util.jwt;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Component
public class JwtPublicKeyPuller {
    private final RestTemplate restTemplate;
    private final JwtKeyHolder keyHolder;
    private final String URL = "https://newlk.kpfu.ru/user-api/token/jwks";

    public JwtPublicKeyPuller(RestTemplate restTemplate, JwtPublicKeyHolder keyHolder) {
        this.restTemplate = restTemplate;
        this.keyHolder = keyHolder;
        // it possibly may cause long context loading due to network request, change to reading from file if needed
        pullKey();
    }

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.DAYS)
    public void pullKey() {
        String key = restTemplate.getForObject(URL, String.class);
        keyHolder.setKey(key);
    }
}
