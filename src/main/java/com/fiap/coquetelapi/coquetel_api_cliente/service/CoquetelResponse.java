package com.fiap.coquetelapi.coquetel_api_cliente.service;

import java.util.List;

public class CoquetelResponse {
    private List<Coquetel> drinks;

    public List<Coquetel> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<Coquetel> drinks) {
        this.drinks = drinks;
    }
}
