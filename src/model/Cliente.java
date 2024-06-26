package model;



public class Cliente {


    private String Nome;
    private String CPF;
    private String Email;
    private int id;

    //Construtor


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente(int id,String nome, String CPF, String email) {
        this.id = id;
        this.Nome = nome;
        this.CPF = CPF;
        this.Email = email;


    }

    //Get and Setter



    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    //toString

    public String toString(){
        return
                this.id+ ";"+
                this.Nome + ";"
                +this.CPF + ";"
                +this.Email +";";


    }
}

