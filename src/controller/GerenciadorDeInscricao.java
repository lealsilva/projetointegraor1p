package controller;

import java.util.ArrayList;
import model.Feira;
import model.Feirante;
import model.Inscricao;

/**
 *
 * @author wilky
 */
public class GerenciadorDeInscricao {
    
   private ArrayList<Inscricao> inscricoes = new ArrayList<>();
   
   public void adicionarInscricao(Inscricao inscricao){
       if(verificarInscricoesExitente(inscricao.getFeira(), inscricao.getFeirante()) == true){
           System.out.println("Feirante ja cadastrado");
       }else{
           inscricoes.add(inscricao);
       }
   }
   
   public int quantidadeDeInscricao(){
       return inscricoes.size();   
   }
   
   public void listarInscricoes(){
       
       for (int indice = 0; indice < inscricoes.size(); indice++) {
           Inscricao inscricao = inscricoes.get(indice);
           System.out.println(inscricao.getFeira().getBairro());
           System.out.println(inscricao.getStatusAprovacao());
           System.out.println(inscricao.getFeirante().getNomeDaBanca());
       }
   }
   
   public void aprovarInscricao(Inscricao inscricao, GerenciadorDaFeira gerenciadorDaFeira){
       
       if(inscricao.getFeira().getId() == gerenciadorDaFeira.getFeira().getId()){
           if("PENDENTE".equalsIgnoreCase(inscricao.getStatusAprovacao())){
                boolean valorEmAdicionarFeirante = gerenciadorDaFeira.adicionarFeirante(inscricao.getFeirante(), inscricao.getLinhaBanca(), inscricao.getColunaBanca());
                if(valorEmAdicionarFeirante){
                    inscricao.setStatusAprovacao("APROVADA");
                }else{
                    System.out.println("Banca ja esta ocupada ou posição invalida"); 
                }
            }else{
               System.out.println("A inscrição não esta pendente");
           }
        }else{
           System.out.println("Incompatibilidade de feira");
       }
    } 
   
   public void rejeitarInscricao(Inscricao inscricao, GerenciadorDaFeira gerenciadorDaFeira){
       
       if(inscricao.getFeira().getId() == gerenciadorDaFeira.getFeira().getId()){
           if("PENDENTE".equalsIgnoreCase(inscricao.getStatusAprovacao())){
                inscricao.setStatusAprovacao("REJEITADA");  
            }else{
                System.out.println("A inscrição não está pendente e não pode ser rejeitada");
            }   
        }else{
           System.out.println("Imcompatilidade de feira");
       }
    }
   
   // com esse metodo impedimos se serem criado inscrições duplicadas
   
   public boolean verificarInscricoesExitente(Feira feira, Feirante feirante){
       
       for (int indice = 0; indice < inscricoes.size(); indice++) {
           Inscricao inscricao = inscricoes.get(indice);
           if(feirante.getId() == inscricao.getFeirante().getId() && feira.getId() == inscricao.getFeira().getId()){
               if("PENDENTE".equalsIgnoreCase(inscricao.getStatusAprovacao()) || "APROVADA".equalsIgnoreCase(inscricao.getStatusAprovacao())){
                   return true;           
               }
           }
       }
       return false;
   }
}   


