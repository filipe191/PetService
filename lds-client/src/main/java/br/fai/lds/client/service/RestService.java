package br.fai.lds.client.service;

import org.springframework.http.HttpHeaders;

public interface RestService {

    HttpHeaders getAuthenticatedHeaders(String usename, String password);

    HttpHeaders getRequestHeaders();
}
