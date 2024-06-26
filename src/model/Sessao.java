package model;


import java.time.LocalDateTime;

public class Sessao {
    private String nomeSessao;
    private String horaDeInicio;
    private int  idFilme;
    private int idSessao;

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    private Cinema cinema;
    private Sala sala;



    @Override
    public String toString() {
        return
                    nomeSessao +
                ";" + horaDeInicio +
                ";" + idSessao ;

    }

    public int getIdSessao() {
        return idSessao;
    }

    public void setIdSessao(int idSessao) {
        this.idSessao = idSessao;
    }

    public Sessao(int idSessao, String nomeSessao, String horaDeInicio) {
        this.nomeSessao = nomeSessao;
        this.horaDeInicio = horaDeInicio;
        this.idSessao=idSessao;


    }

    public String getNomeSessao() {
        return nomeSessao;
    }

    public void setNomeSessao(String nomeSessao) {
        this.nomeSessao = nomeSessao;
    }

    public String getHoraDeInicio() {
        return horaDeInicio;
    }

    public void setHoraDeInicio(String horaDeInicio) {
        this.horaDeInicio = horaDeInicio;
    }



    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }
}
