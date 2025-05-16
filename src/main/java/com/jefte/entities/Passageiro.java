package com.jefte.entities;

public class Passageiro {
    private int id;
    private String nome;
    private String cpf;
    private String email;

    public Passageiro() {
    }

    public Passageiro(int id, String nome, String cpf, String email) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        if (!isValidCPF(cpf)) {
            throw new IllegalArgumentException("Invalid CPF");
        }
        this.cpf = cpf;
    }

    public Passageiro correct(){
        isValidCPF(this.cpf);
        isValidEmail(this.email);
        return this;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private boolean isValidCPF(String cpf) {
        if (cpf == null || cpf.length() != 11) {
            return false;
        }

        int[] digits = new int[11];
        for (int i = 0; i < 11; i++) {
            digits[i] = Integer.parseInt(String.valueOf(cpf.charAt(i)));
        }

        int sum = 0;
        int weight = 10;
        for (int i = 0; i < 9; i++) {
            sum += digits[i] * weight;
            weight--;
        }

        int checkingDigit = 11 - sum % 11;
        if (checkingDigit > 9) {
            checkingDigit = 0;
        }

        if (digits[9] != checkingDigit) {
            return false;
        }

        sum = 0;
        weight = 11;
        for (int i = 0; i < 10; i++) {
            sum += digits[i] * weight;
            weight--;
        }

        checkingDigit = 11 - sum % 11;
        if (checkingDigit > 9) {
            checkingDigit = 0;
        }

        return digits[10] == checkingDigit;
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

}