package persistance;

import model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class GenericDAO {

    public static <T> void salvarObjetosEmArquivo(List<T> listaObjetos, String nomeArquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {

            for (T objeto : listaObjetos) {
                writer.write(objeto.toString());
                writer.newLine();
            }

            System.out.println("Arquivo " + nomeArquivo + " criado com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo " + nomeArquivo + ": " + e.getMessage());
        }
    }
    public static <T> List<T> lerDoArquivo(String fileName, Function<String, T> mapper) throws IOException {
        List<T> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(mapper.apply(line));
            }
        }
        return list;
    }

    public static Sala mapToSala(String line) {
        String[] parts = line.split(";");
        int idSala = Integer.parseInt(parts[4]);
        String nomeDaSala = parts[0];
        int capacidade = Integer.parseInt(parts[1]);
        String assentoLinha = parts[2];
        int assentoColuna = Integer.parseInt(parts[3]);
        return new Sala(idSala, nomeDaSala, capacidade, assentoLinha, assentoColuna);
    }

    public static Filme mapToFilme(String line) {
        String[] parts = line.split(";");
        int idFilme = Integer.parseInt(parts[2]);
        String titulo = parts[0].replace("'", "");
        float duracao = Float.parseFloat(parts[1]);
        return new Filme(idFilme, titulo, duracao);
    }
    public static Bilhete mapToBilhete(String line) {
        String[] parts = line.split(";");
        int numeroBilhete = Integer.parseInt(parts[0]);
        int idCliente = Integer.parseInt(parts[1]);
        int idSessao = Integer.parseInt(parts[2]);
        return new Bilhete(numeroBilhete, idCliente, idSessao);
    }

    public static Cinema mapToCinema(String line) {
        String[] parts = line.split(";");
        int idCinema = Integer.parseInt(parts[1]);
        String nomeCinema = parts[0];
        return new Cinema(idCinema, nomeCinema);
    }

    public static Cliente mapToCliente(String line) {
        String[] parts = line.split(";");
        if (parts.length != 4) {
            throw new IllegalArgumentException("Formato de linha inválido para Cliente: " + line);
        }
        int id = Integer.parseInt(parts[0]);
        String nome = parts[1];
        String cpf = parts[2];
        String email = parts[3];
        return new Cliente(id, nome, cpf, email);
    }
    public static Sessao mapToSessao(String line) {
        String[] parts = line.split(";");
        if (parts.length != 3) {  // Ajustar se o número de partes esperado for diferente
            throw new IllegalArgumentException("Formato de linha inválido para Sessao: " + line);
        }
        int idSessao = Integer.parseInt(parts[2]);
        String nomeSessao = parts[0];
        String horaDeInicio = parts[1];
        return new Sessao(idSessao, nomeSessao, horaDeInicio);
    }
}
