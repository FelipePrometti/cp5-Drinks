package com.fiap.coquetelapi.coquetel_api_cliente.service;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class CoquetelService {

    public List<Coquetel> buscarCoquetel(String nomeCoquetel) {
        try {
            String url = "https://www.thecocktaildb.com/api/json/v1/1/search.php?s=" + nomeCoquetel;
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            CoquetelResponse coquetelResponse = new Gson().fromJson(response.body(), CoquetelResponse.class);
            return coquetelResponse.getDrinks();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao buscar coquetel: " + e.getMessage(), e);
        } catch (InterruptedException e) {
            throw new RuntimeException("Erro ao buscar coquetel: " + e.getMessage(), e);
        }
    }
}
