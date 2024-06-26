package main;

import model.*;
import persistance.*;


import java.io.IOException;
import java.util.List;

import static persistance.GenericDAO.salvarObjetosEmArquivo;


public class Mainteste {

    public static void main(String[] args) {
        ConexaoJDBC conexaoJDBC = new ConexaoJDBC();

        /*Cinema cinema = new Cinema(8,"Flamboyant ");
        CinemaDAO a = new CinemaDAO(conexaoJDBC);
        a.inserir(cinema);


        BilheteDAO bilheteDAO = new BilheteDAO(conexaoJDBC);
        bilheteDAO.excluir(2);

        FilmeDAO filmeDAO = new FilmeDAO(conexaoJDBC);
        Filme filme =filmeDAO.localizarID(2);
        filme.setTitulo("A espera de um ligagre");
        filmeDAO.alterar(filme);
        SalaDAO salaDAO = new SalaDAO(conexaoJDBC);
        List<Sala> salas = salaDAO.localizarTodos();
        for(int i=0; i< salas.size();i++){
            System.out.println(salas.get(i).getNomeDaSala());
        }
        SessaoDAO sessaoDAO = new SessaoDAO(conexaoJDBC);
        System.out.println(sessaoDAO.localizarID(4).getNomeSessao());*/



        ClienteDAO clienteDAO = new ClienteDAO(conexaoJDBC);
        FilmeDAO filmeDAO = new FilmeDAO(conexaoJDBC);
        BilheteDAO bilheteDAO = new BilheteDAO(conexaoJDBC);
        CinemaDAO  cinemaDAO = new CinemaDAO( conexaoJDBC);
        SalaDAO salaDAO = new SalaDAO( conexaoJDBC);
        SessaoDAO sessaoDAO =  new SessaoDAO(conexaoJDBC);
        salvarObjetosEmArquivo(cinemaDAO.localizarTodos(), "cinema.txt");
        salvarObjetosEmArquivo(clienteDAO.localizarTodos(), "cliente.txt");
        salvarObjetosEmArquivo(sessaoDAO.localizarTodos(), "sessao.txt");
        salvarObjetosEmArquivo(bilheteDAO.localizarTodos(), "bilhete.txt");
        salvarObjetosEmArquivo(filmeDAO.localizarTodos(), "filme.txt");
        salvarObjetosEmArquivo(salaDAO.localizarTodos(), "sala.txt");





        try {
            List<Sala> salas = GenericDAO.lerDoArquivo("sala.txt", GenericDAO::mapToSala);
            List<Cliente> clientes = GenericDAO.lerDoArquivo("cliente.txt", GenericDAO::mapToCliente);
            List<Filme> filmes = GenericDAO.lerDoArquivo("filme.txt", GenericDAO::mapToFilme);
            List<Bilhete> bilhetes = GenericDAO.lerDoArquivo("bilhete.txt", GenericDAO::mapToBilhete);
            List<Sessao> sessoes = GenericDAO.lerDoArquivo("sessao.txt", GenericDAO::mapToSessao);
            List<Cinema> cinemas = GenericDAO.lerDoArquivo("cinema.txt", GenericDAO::mapToCinema);
            for (Sala sala : salas) {
                System.out.println(sala);
            }
            for (Filme filme : filmes) {
                System.out.println(filme);
            }


        for (int i = 0;i<clientes.size();i++) {
            System.out.println(clientes.get(i));
        }
            for (Bilhete bilhete : bilhetes) {
                System.out.println(bilhete);
            }
            for (Sessao sessao : sessoes) {
                System.out.println(sessao);
            }
            for (Cinema cinema : cinemas) {
                System.out.println(cinema);
            }



    } catch (IOException e) {
        e.printStackTrace();
    }

    }
    }

