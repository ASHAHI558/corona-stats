package com.example.coronastats.rapidapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RapidApiController {

    @Value("${rapidapi.uri}")
    private String uri;

    @Value("${rapidapi.headerHostName}")
    private String headerHostName;

    @Value("${rapidapi.headerHostValue}")
    private String headerHostValue;

    @Value("${rapidapi.headerKeyName}")
    private String headerKeyName;

    @Value("${rapidapi.headerKeyValue}")
    private String headerKeyValue;

    @Autowired
    private RestTemplate restTemplate;

    public RapidApiResponseDto getAllCountriesData() {
        return restTemplate.exchange(uri, HttpMethod.GET, getHttpEntity(), RapidApiResponseDto.class).getBody();
    }

    public RapidApiResponseDto getDataByCountry(String country) {
        String url = uri.concat("?country=").concat(country);
        return restTemplate.exchange(url, HttpMethod.GET, getHttpEntity(), RapidApiResponseDto.class).getBody();
    }

    private HttpEntity<?> getHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(headerHostName, headerHostValue);
        headers.add(headerKeyName, headerKeyValue);
        return new HttpEntity<>(headers);
    }

}
