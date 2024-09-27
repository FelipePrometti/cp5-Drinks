package com.fiap.coquetelapi.coquetel_api_cliente.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection obterConexao () {
        Connection conexao = null;
        String usuario = "RM558876";
        String senha = "090306";

        try {
            conexao = DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl",
                    usuario, senha);
        } catch (SQLException erro){
            erro.printStackTrace();
            System.out.println("erro na conexão");
        }

        return conexao;
    }
}
