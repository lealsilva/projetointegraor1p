package teste;

import model.Feira;
import model.Feirante;
import model.Inscricao;
import controller.GerenciadorDaFeira;
import controller.GerenciadorDeInscricao;

public class TesteInscricao {

    public static void main(String[] args) {

        // Criar uma feira
        Feira feira = new Feira(1, "Feira Central", "Centro",0.0, 0.0, 3, 4);

        // Criar dois feirantes
        Feirante feirante1 = new Feirante(1, "João", "Frutas do João","Alimentação", "LIC001", "APROVADO");

        Feirante feirante2 = new Feirante(2, "Maria", "Artesanato da Maria","Artesanato", "LIC002", "APROVADO");

        // Criar os gerenciadores
        GerenciadorDaFeira gerenciadorDaFeira = new GerenciadorDaFeira(feira);

        GerenciadorDeInscricao gerenciadorDeInscricao =  new GerenciadorDeInscricao();
        
        Inscricao inscricao = new Inscricao(1, feira, feirante1, "PENDENTE", 0, 0);
        Inscricao inscricao2 = new Inscricao(2, feira, feirante2, "PENDENTE", 0, 0);
        Inscricao inscricao3 = new Inscricao(3, feira, feirante2, "PENDENTE", 1, 0);
        Inscricao inscricao4 = new Inscricao(4, feira, feirante2, "PENDENTE", 0, 1);
        
        gerenciadorDeInscricao.adicionarInscricao(inscricao);
        gerenciadorDeInscricao.aprovarInscricao(inscricao, gerenciadorDaFeira);

        gerenciadorDeInscricao.adicionarInscricao(inscricao2);
        gerenciadorDeInscricao.aprovarInscricao(inscricao2, gerenciadorDaFeira);
        gerenciadorDeInscricao.rejeitarInscricao(inscricao2, gerenciadorDaFeira);

        gerenciadorDeInscricao.adicionarInscricao(inscricao3);
        gerenciadorDeInscricao.rejeitarInscricao(inscricao3, gerenciadorDaFeira);

        gerenciadorDeInscricao.adicionarInscricao(inscricao4);
        gerenciadorDeInscricao.aprovarInscricao(inscricao4, gerenciadorDaFeira);
        
        System.out.println(gerenciadorDeInscricao.quantidadeDeInscricao());
        
        System.out.println(inscricao.getStatusAprovacao());
        System.out.println(inscricao2.getStatusAprovacao());
        System.out.println(inscricao3.getStatusAprovacao());
        System.out.println(inscricao4.getStatusAprovacao());
        
        gerenciadorDaFeira.mostrarBanca();
        
        
    }
}