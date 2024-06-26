package model;

public class Filme {
    private String titulo;
    private float duracao;
    private int idFilme;

    public Filme(int idFilme,String titulo, float duracao) {
        this.titulo = titulo;
        this.duracao = duracao;
        this.idFilme = idFilme;
    }

    public int getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(int idFilme) {
        this.idFilme = idFilme;
    }

    public String getTitulo() {
        return titulo;
    }


    public String toString() {
        return   this.titulo+
                ";" + this.duracao +
                ";" + this.idFilme+";";
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public float getDuracao() {
        return duracao;
    }

    public void setDuracao(float duracao) {
        this.duracao = duracao;
    }
}
