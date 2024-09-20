package com.fiap.coquetelapi.coquetel_api_cliente;

import com.fiap.coquetelapi.coquetel_api_cliente.json.EscritaJson;
import com.fiap.coquetelapi.coquetel_api_cliente.service.Coquetel;
import com.fiap.coquetelapi.coquetel_api_cliente.service.CoquetelService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CoquetelService coquetelService = new CoquetelService();

        System.out.print("Digite o nome do coquetel\n(Se o coquetel tiver espaço no nome, use %20. Por exemplo, 'Bloody%20Mary'):");
        String nomeCoquetel = scanner.nextLine();

        List<Coquetel> coqueteis = coquetelService.buscarCoquetel(nomeCoquetel);

        if (coqueteis != null && !coqueteis.isEmpty()) {
            for (Coquetel coquetel : coqueteis) {
                System.out.println("ID: " + coquetel.getIdDrink());
                System.out.println("Nome: " + coquetel.getStrDrink());
                System.out.println("Instruções: " + coquetel.getStrInstructions());
                System.out.println("Imagem: " + coquetel.getStrDrinkThumb());
                System.out.println("Categoria: " + coquetel.getStrCategory());
                System.out.println("Tipo: " + coquetel.getStrAlcoholic());
                System.out.println();
            }

            EscritaJson.escreverCoqueteisParaJson(coqueteis);
        } else {
            System.out.println("Nenhum coquetel encontrado com o nome: " + nomeCoquetel);
        }

        scanner.close();
    }
}
