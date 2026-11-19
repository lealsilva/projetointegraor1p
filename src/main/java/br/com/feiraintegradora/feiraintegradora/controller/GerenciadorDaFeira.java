package br.com.feiraintegradora.feiraintegradora.controller;


import br.com.feiraintegradora.feiraintegradora.model.Feira;
import br.com.feiraintegradora.feiraintegradora.model.Feirante;


public class GerenciadorDaFeira {
    
    private Feira feira;
    
    private Feirante [][] matrizBancas;
    
    public GerenciadorDaFeira(Feira feira){
        this.feira = feira;
        this.matrizBancas = new Feirante[feira.getQuantidadeDeLinhas()][feira.getQuantidadeDeColunas()];
    }
    
    // mostrar a matriz princial do "espaço feira";

    public void mostrarBanca(){
        
        for (int linha = 0; linha < matrizBancas.length; linha++) {
            for (int coluna = 0; coluna < matrizBancas[linha].length; coluna++) {
                if(matrizBancas[linha][coluna] == null){
                    System.out.println("Posição [" + linha + "][" + coluna + "]: Banca vazia");
                }else{
                    System.out.println("posção [" + linha +"] ["+ coluna +"] "+matrizBancas[linha][coluna].getNomeDaBanca());
                }
            } 
        }
    }
    
    // verificando se o espaço dentro da matriz esta diponivel;
    
    public boolean verificarDiponibilidade(int linha, int coluna){
        
        if(linha >= 0 && linha < matrizBancas.length && coluna >= 0 && coluna < matrizBancas[linha].length){
            if(matrizBancas[linha][coluna] == null){
                return true;
            }
        }
        
        return false;
    }
    
    // metodo para adicionar o feirante;
    
    public boolean adicionarFeirante(Feirante feirante , int linha, int coluna){
        
        if(verificarDiponibilidade (linha, coluna)){
            matrizBancas[linha][coluna] = feirante;
            
            return true;
        }
        
        return false;
    }
    
    // metodo para remover feirante da feira;
    
    public void removerFeirante(int linha, int coluna){
        
        if(linha >= 0 && linha < matrizBancas.length && coluna >= 0 && coluna < matrizBancas[linha].length){
            if(matrizBancas[linha][coluna] != null){
                matrizBancas[linha][coluna] = null;
                System.out.println("Feirante removido");
            }
            else{
                System.out.println("Espaço ja esta vazio");
            }
        }else{
            System.out.println("Espaço inexistente");
        }
    }
    
    // metodo para verifica a valor que esta dentro de "feira"
    
    public Feira getFeira(){
        return feira;
    }

}