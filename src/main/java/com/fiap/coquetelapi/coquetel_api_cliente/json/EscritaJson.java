package com.fiap.coquetelapi.coquetel_api_cliente.json;

import com.fiap.coquetelapi.coquetel_api_cliente.service.Coquetel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class EscritaJson {
    public static void escreverCoqueteisParaJson(List<Coquetel> coqueteis) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter("coqueteis.json")) {
            gson.toJson(coqueteis, writer);
            System.out.println("Arquivo JSON escrito com sucesso!");
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao escrever o arquivo: " + e.getMessage());
        }
    }
}
