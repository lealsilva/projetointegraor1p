
package br.com.feiraintegradora.feiraintegradora.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Feirante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nomeDoResponsavel;
    private String nomeDaBanca;
    private String categoriaDaBanca;
    private String numeroDaLicenca;
    private String statusAprovacao;
    private String email;
    private String senha;
    private String telefone;
    private String cpfCnpj;
    private String endereco;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeDoResponsavel() {
        return nomeDoResponsavel;
    }

    public void setNomeDoResponsavel(String nomeDoResponsavel) {
        this.nomeDoResponsavel = nomeDoResponsavel;
    }

    public String getNomeDaBanca() {
        return nomeDaBanca;
    }

    public void setNomeDaBanca(String nomeDaBanca) {
        this.nomeDaBanca = nomeDaBanca;
    }

    public String getCategoriaDaBanca() {
        return categoriaDaBanca;
    }

    public void setCategoriaDaBanca(String categoriaDaBanca) {
        this.categoriaDaBanca = categoriaDaBanca;
    }

    public String getNumeroDaLicenca() {
        return numeroDaLicenca;
    }

    public void setNumeroDaLicenca(String numeroDaLicenca) {
        this.numeroDaLicenca = numeroDaLicenca;
    }

    public String getStatusAprovacao() {
        return statusAprovacao;
    }

    public void setStatusAprovacao(String statusAprovacao) {
        this.statusAprovacao = statusAprovacao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    
    public Feirante() {

    }

    public Feirante(int id, String nomeDoResponsavel, String nomeDaBanca, String categoriaDaBanca, String numeroDaLicenca, String statusAprovacao, String email, String senha, String telefone, String cpfCnpj, String endereco) {
        this.id = id;
        this.nomeDoResponsavel = nomeDoResponsavel;
        this.nomeDaBanca = nomeDaBanca;
        this.categoriaDaBanca = categoriaDaBanca;
        this.numeroDaLicenca = numeroDaLicenca;
        this.statusAprovacao = statusAprovacao;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.cpfCnpj = cpfCnpj;
        this.endereco = endereco;
    }
    
    
}

  