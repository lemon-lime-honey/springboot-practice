package com.springboot.rest.service;

import java.net.URI;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.springboot.rest.dto.MemberDto;

@Service
public class RestTemplateService {
    public String getName() {
        URI uri = UriComponentsBuilder
            .fromUriString("http://localhost:9090")
            .path("/api/v1/crud-api")
            .encode()
            .build()
            .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

        return responseEntity.getBody();
    }

    public String getNameWithPathVariable() {
        URI uri = UriComponentsBuilder
            .fromUriString("http://localhost:9090")
            .path("/api/v1/crud-api/{name}")
            .encode()
            .build()
            .expand("Anakin")
            .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

        return responseEntity.getBody();
    }

    public String getNameWithParameter() {
        URI uri = UriComponentsBuilder
            .fromUriString("http://localhost:9090")
            .path("/api/v1/crud-api/param")
            .queryParam("name", "Anakin")
            .encode()
            .build()
            .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

        return responseEntity.getBody();
    }

    public ResponseEntity<MemberDto> postWithParamAndBody() {
        URI uri = UriComponentsBuilder
            .fromUriString("http://localhost:9090")
            .path("/api/v1/crud-api")
            .queryParam("name", "Anakin")
            .queryParam("email", "skywalker@jedi.org")
            .queryParam("organization", "Jedi")
            .encode()
            .build()
            .toUri();

        MemberDto memberDto = new MemberDto();
        memberDto.setName("Anakin");
        memberDto.setEmail("skywalker@jedi.org");
        memberDto.setOrganization("Jedi");

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MemberDto> responseEntity = restTemplate.postForEntity(uri, memberDto, MemberDto.class);

        return responseEntity;
    }

    public ResponseEntity<MemberDto> postWithHeader() {
        URI uri = UriComponentsBuilder
            .fromUriString("http://localhost:9090")
            .path("/api/v1/crud-api/add-header")
            .encode()
            .build()
            .toUri();

        MemberDto memberDto = new MemberDto();
        memberDto.setName("Anakin");
        memberDto.setEmail("skywalker@jedi.org");
        memberDto.setOrganization("Jedi");

        RequestEntity<MemberDto> requestEntity = RequestEntity
            .post(uri)
            .header("my-header", "Coruscant API")
            .body(memberDto);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MemberDto> responseEntity = restTemplate.exchange(requestEntity, MemberDto.class);

        return responseEntity;
    }

    public RestTemplate restTemplate() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();

        HttpClient client = HttpClientBuilder.create()
            .setConnectionManager(PoolingHttpClientConnectionManagerBuilder.create()
                .setMaxConnTotal(500)
                .setMaxConnPerRoute(500)
                .build())
            .build();

        CloseableHttpClient httpClient = HttpClients.custom()
            .setConnectionManager(PoolingHttpClientConnectionManagerBuilder.create()
                .setMaxConnTotal(500)
                .setMaxConnPerRoute(500)
                .build())
            .build();

        factory.setHttpClient(httpClient);
        factory.setConnectTimeout(2000);

        RestTemplate restTemplate = new RestTemplate(factory);

        return restTemplate;
    }
}