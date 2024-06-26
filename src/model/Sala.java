package model;

public class Sala {
    private String nomeDaSala;
    private int capacidade;
    private String assentoLinha;
    private int assentoColuna;
    private int idSala;



    public Sala(int idSala,String nomeDaSala, int capacidade, String assentoLinha, int assentoColuna) {
        this.nomeDaSala = nomeDaSala;
        this.capacidade = capacidade;
        this.assentoLinha = assentoLinha;
        this.assentoColuna = assentoColuna;
        this.idSala =idSala;

    }

    @Override
    public String toString() {
        return
                nomeDaSala +
                ";" + capacidade +
                ";" + assentoLinha +
                ";" + assentoColuna +
                ";" + idSala ;
    }

    public int getIdSala() {
        return idSala;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public String getNomeDaSala() {
        return nomeDaSala;
    }

    public void setNomeDaSala(String nomeDaSala) {
        this.nomeDaSala = nomeDaSala;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public String getAssentoLinha() {
        return assentoLinha;
    }

    public void setAssentoLinha(String assentoLinha) {
        this.assentoLinha = assentoLinha;
    }

    public int getAssentoColuna() {
        return assentoColuna;
    }

    public void setAssentoColuna(int assentoColuna) {
        this.assentoColuna = assentoColuna;
    }
}
