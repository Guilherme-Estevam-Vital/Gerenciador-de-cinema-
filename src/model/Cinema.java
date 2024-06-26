package model;

import java.util.ArrayList;
import java.util.List;

public class Cinema {
    private String nomeCinema;
    private int idCinema;
    private List<Sala> salas;

    public int getIdCinema() {
        return idCinema;
    }

    @Override
    public String toString() {
        return
                nomeCinema  +
                ";" + idCinema  ;
    }

    public void setIdCinema(int idCinema) {
        this.idCinema = idCinema;
    }

    public Cinema(int idCinema, String nomeCinema) {
        this.nomeCinema = nomeCinema;
        this.idCinema = idCinema;
        this.salas = new ArrayList<>();
    }

    public String getNomeCinema() {
        return nomeCinema;
    }

    public void setNomeCinema(String nomeCinema) {
        this.nomeCinema = nomeCinema;
    }

    public List<Sala> getSalas() {
        return salas;
    }

    public void adicionarSala(Sala sala) {
        this.salas.add(sala);
    }

    public void removerSala(Sala sala) {
        this.salas.remove(sala);
    }

    public Sala getSalaPorNome(String nomeDaSala) {
        for (Sala sala : salas) {
            if (sala.getNomeDaSala().equals(nomeDaSala)) {
                return sala;
            }
        }
        return null;
    }
}
