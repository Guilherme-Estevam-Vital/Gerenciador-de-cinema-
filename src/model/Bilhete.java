    package model;

    public class Bilhete {
        private int numeroBilhete;
        private int idCliente;
        private int idSessao;

        // Construtor
        public Bilhete(int numeroBilhete, int idCliente, int idSessao) {
            this.numeroBilhete = numeroBilhete;
            this.idSessao = idSessao;
            this.idCliente = idCliente;

        }

        public int getIdCliente() {
            return idCliente;
        }

        public void setIdCliente(int idCliente) {
            this.idCliente = idCliente;
        }

        public int getIdSessao() {
            return idSessao;
        }

        public void setIdSessao(int idSessao) {
            this.idSessao = idSessao;
        }

        @Override
        public String toString() {
            return
                    numeroBilhete +
                    ";" + idCliente +
                    ";" + idSessao
                    ;
        }

        public Bilhete(int numeroBilhete) {
            this.numeroBilhete = numeroBilhete;

        }

        // Getters e Setters
        public int getNumeroBilhete() {
            return numeroBilhete;
        }

        public void setNumeroBilhete(int numeroBilhete) {
            this.numeroBilhete = numeroBilhete;
        }






    }
