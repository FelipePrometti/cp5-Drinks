package com.fiap.coquetelapi.coquetel_api_cliente.dao;

import com.fiap.coquetelapi.coquetel_api_cliente.service.Coquetel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoquetelDAO {

    private Connection connection;

    public CoquetelDAO(Connection connection) {
        this.connection = connection;
    }

    public void inserirCoquetel(Coquetel coquetel) throws SQLException {
        String sql = "INSERT INTO coqueteis (idDrink, strDrink, strInstructions, strDrinkThumb, strCategory, strAlcoholic) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, coquetel.getIdDrink());
            stmt.setString(2, coquetel.getStrDrink());
            stmt.setString(3, coquetel.getStrInstructions());
            stmt.setString(4, coquetel.getStrDrinkThumb());
            stmt.setString(5, coquetel.getStrCategory());
            stmt.setString(6, coquetel.getStrAlcoholic());

            stmt.executeUpdate();
        }
    }

    public Coquetel buscarCoquetelPorId(String idDrink) throws SQLException {
        String sql = "SELECT * FROM coqueteis WHERE idDrink = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, idDrink);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Coquetel(
                            rs.getString("idDrink"),
                            rs.getString("strDrink"),
                            rs.getString("strInstructions"),
                            rs.getString("strDrinkThumb"),
                            rs.getString("strCategory"),
                            rs.getString("strAlcoholic")
                    );
                }
            }
        }
        return null;
    }

    public List<Coquetel> listarTodosCoqueteis() throws SQLException {
        List<Coquetel> coqueteis = new ArrayList<>();
        String sql = "SELECT * FROM coqueteis";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Coquetel coquetel = new Coquetel(
                        rs.getString("idDrink"),
                        rs.getString("strDrink"),
                        rs.getString("strInstructions"),
                        rs.getString("strDrinkThumb"),
                        rs.getString("strCategory"),
                        rs.getString("strAlcoholic")
                );
                coqueteis.add(coquetel);
            }
        }

        return coqueteis;
    }

    public List<Coquetel> buscarCoquetelPorNome(String strDrink) throws SQLException {
        String sql = "SELECT * FROM coqueteis WHERE strDrink LIKE ?";
        List<Coquetel> coqueteis = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + strDrink + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Coquetel coquetel = new Coquetel(
                            rs.getString("idDrink"),
                            rs.getString("strDrink"),
                            rs.getString("strInstructions"),
                            rs.getString("strDrinkThumb"),
                            rs.getString("strCategory"),
                            rs.getString("strAlcoholic")
                    );
                    coqueteis.add(coquetel);
                }
            }
        }

        return coqueteis;
    }
}
