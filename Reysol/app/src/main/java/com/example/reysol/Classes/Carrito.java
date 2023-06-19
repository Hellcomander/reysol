package com.example.reysol.Classes;

public class Carrito {
    private int id;
    private int idUsuario;
    private double total;
    private boolean finalizado;
    private String idPaqueteria;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    public String getIdPaqueteria() {
        return idPaqueteria;
    }

    public void setIdPaqueteria(String idPaqueteria) {
        this.idPaqueteria = idPaqueteria;
    }
}
