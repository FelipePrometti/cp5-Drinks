//RM555174 - Felipe Menezes Prometti
//RM558876 - Samuel Damasceno Silva

package com.fiap.coquetelapi.coquetel_api_cliente;

import com.fiap.coquetelapi.coquetel_api_cliente.dao.CoquetelDAO;
import com.fiap.coquetelapi.coquetel_api_cliente.dao.ConnectionFactory;
import com.fiap.coquetelapi.coquetel_api_cliente.service.Coquetel;
import com.fiap.coquetelapi.coquetel_api_cliente.service.CoquetelService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Connection connection = ConnectionFactory.obterConexao()) {
            CoquetelDAO coquetelDAO = new CoquetelDAO(connection);
            CoquetelService coquetelService = new CoquetelService();
            Scanner scanner = new Scanner(System.in);

            List<Coquetel> todosCoqueteis = coquetelService.buscarTodosCoqueteis();
            if (todosCoqueteis != null && !todosCoqueteis.isEmpty()) {
                for (Coquetel coquetel : todosCoqueteis) {
                    coquetelDAO.inserirCoquetel(coquetel);
                }
                System.out.println("Todos os coquetéis foram adicionados ao banco de dados.");
            } else {
                System.out.println("Nenhum coquetel foi encontrado na API.");
            }

            while (true) {
                System.out.println("====== MENU =====");
                System.out.println("1. Cadastrar um novo coquetel");
                System.out.println("2. Listar todos os coquetéis");
                System.out.println("3. Buscar coquetel por ID");
                System.out.println("4. Buscar coquetel por nome");
                System.out.println("5. Sair");
                System.out.print("Escolha uma opção: ");
                int opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        System.out.print("Digite o ID do coquetel: ");
                        String idDrink = scanner.nextLine();
                        System.out.print("Digite o nome do coquetel: ");
                        String strDrink = scanner.nextLine();
                        System.out.print("Digite as instruções do coquetel: ");
                        String strInstructions = scanner.nextLine();
                        System.out.print("Digite o link da imagem do coquetel: ");
                        String strDrinkThumb = scanner.nextLine();
                        System.out.print("Digite a categoria do coquetel: ");
                        String strCategory = scanner.nextLine();
                        System.out.print("O coquetel é alcoólico? (Alcoholic / Non alcoholic)");
                        String strAlcoholic = scanner.nextLine();

                        Coquetel novoCoquetel = new Coquetel(idDrink, strDrink, strInstructions, strDrinkThumb, strCategory, strAlcoholic);
                        coquetelDAO.inserirCoquetel(novoCoquetel);
                        System.out.println("Coquetel cadastrado com sucesso!");
                        break;

                    case 2:
                        todosCoqueteis = coquetelDAO.listarTodosCoqueteis();
                        if (todosCoqueteis != null && !todosCoqueteis.isEmpty()) {
                            for (Coquetel coquetel : todosCoqueteis) {
                                exibirCoquetel(coquetel);
                            }
                        } else {
                            System.out.println("Nenhum coquetel encontrado no banco de dados.");
                        }
                        break;

                    case 3:
                        System.out.print("Digite o ID do coquetel: ");
                        String idBusca = scanner.nextLine();
                        Coquetel coquetel = coquetelDAO.buscarCoquetelPorId(idBusca);
                        if (coquetel != null) {
                            exibirCoquetel(coquetel);
                        } else {
                            System.out.println("Coquetel não encontrado.");
                        }
                        break;

                    case 4:
                        System.out.print("Digite o nome do coquetel para buscar: ");
                        String nomeBusca = scanner.nextLine();
                        List<Coquetel> coqueteisPorNome = coquetelDAO.buscarCoquetelPorNome(nomeBusca);

                        if (coqueteisPorNome != null && !coqueteisPorNome.isEmpty()) {
                            for (Coquetel c : coqueteisPorNome) {
                                exibirCoquetel(c);
                            }
                        } else {
                            System.out.println("Nenhum coquetel encontrado com esse nome.");
                        }
                        break;

                    case 5:
                        System.out.println("Esperamos ter ajudado!");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao conectar ao banco: " + e.getMessage());
        }
    }

    private static void exibirCoquetel(Coquetel coquetel) {
        System.out.println("ID: " + coquetel.getIdDrink());
        System.out.println("Nome: " + coquetel.getStrDrink());
        System.out.println("Instruções: " + coquetel.getStrInstructions());
        System.out.println("Imagem: " + coquetel.getStrDrinkThumb());
        System.out.println("Categoria: " + coquetel.getStrCategory());
        System.out.println("Tipo: " + coquetel.getStrAlcoholic());
        System.out.println();
    }
}
