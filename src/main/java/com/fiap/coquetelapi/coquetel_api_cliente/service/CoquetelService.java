package com.fiap.coquetelapi.coquetel_api_cliente.service;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.ArrayList;

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

    // Como a nossa API não tinha um endpoint que retornasse todos os coqueteis, usamos um endpoint que retorna os coqueteis por letra
    public List<Coquetel> buscarTodosCoqueteis() {
        List<Coquetel> todosCoqueteis = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        Gson gson = new Gson();

        String[] letras = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
                "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

        for (String letra : letras) {
            String url = "https://www.thecocktaildb.com/api/json/v1/1/search.php?f=" + letra;

            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                CoquetelResponse coquetelResponse = gson.fromJson(response.body(), CoquetelResponse.class);

                if (coquetelResponse.getDrinks() != null) {
                    todosCoqueteis.addAll(coquetelResponse.getDrinks());
                }

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                System.out.println("Erro ao buscar coquetéis: " + e.getMessage());
            }
        }

        return todosCoqueteis;
    }

}
