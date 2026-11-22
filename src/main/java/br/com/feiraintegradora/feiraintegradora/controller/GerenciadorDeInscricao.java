
package br.com.feiraintegradora.feiraintegradora.controller;

import br.com.feiraintegradora.feiraintegradora.model.Feira;
import br.com.feiraintegradora.feiraintegradora.model.Feirante;
import br.com.feiraintegradora.feiraintegradora.model.Inscricao;
import br.com.feiraintegradora.feiraintegradora.repository.InscricaoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GerenciadorDeInscricao {

    @Autowired
    private InscricaoRepository inscricaoRepository;

    public void adicionarInscricao(Inscricao inscricao) {

    Feirante feirante = inscricao.getFeirante();
    Feira feira = inscricao.getFeira();
    if (!"APROVADO".equalsIgnoreCase(feirante.getStatusAprovacao())){
        System.out.println("Feirante não possui cadastro aprovado.");
        return;
    }
    
    if (verificarInscricoesExitente(feira, feirante)){
        System.out.println("Feirante já cadastrado nesta feira.");
        return;
    }
    
        GerenciadorDaFeira gerenciador = reconstruirMatriz(feira);
        boolean bancaEncontrada =gerenciador.adicionarFeiranteAutomaticamente(feirante, inscricao);
        if (!bancaEncontrada) {
            System.out.println("Não existem bancas disponíveis nesta feira.");
            return;
        }
        inscricao.setStatusAprovacao("APROVADA");
        inscricaoRepository.save(inscricao);
        System.out.println("Inscrição realizada com sucesso!");
    }

    public long quantidadeDeInscricao() {
        return inscricaoRepository.count();
    }

    public void listarInscricoes() {

        List<Inscricao> inscricoes = inscricaoRepository.findAll();

        for (int indice = 0; indice < inscricoes.size(); indice++) {
            Inscricao inscricao = inscricoes.get(indice);

            System.out.println(inscricao.getFeira().getBairro());
            System.out.println(inscricao.getStatusAprovacao());
            System.out.println(inscricao.getFeirante().getNomeDaBanca());
        }
    }
    
    public GerenciadorDaFeira reconstruirMatriz(Feira feira) {

    GerenciadorDaFeira gerenciador = new GerenciadorDaFeira(feira);

    List<Inscricao> inscricoes =
            inscricaoRepository.findAllByFeira_IdAndStatusAprovacaoIgnoreCase(
                    feira.getId(), "APROVADA");

    for (Inscricao inscricao : inscricoes) {

        gerenciador.adicionarFeirante(
                inscricao.getFeirante(),
                inscricao.getLinhaBanca(),
                inscricao.getColunaBanca()
        );
    }

    return gerenciador;
}

    public void aprovarInscricao(Inscricao inscricao,GerenciadorDaFeira gerenciadorDaFeira) {

        if (inscricao.getFeira().getId() == gerenciadorDaFeira.getFeira().getId()) {
            if ("PENDENTE".equalsIgnoreCase(inscricao.getStatusAprovacao())) {
                boolean valorEmAdicionarFeirante = gerenciadorDaFeira.adicionarFeirante(inscricao.getFeirante(),inscricao.getLinhaBanca(),inscricao.getColunaBanca());
                if (valorEmAdicionarFeirante){
                    inscricao.setStatusAprovacao("APROVADA");
                    inscricaoRepository.save(inscricao);
                } else {
                    System.out.println("Banca ja esta ocupada ou posição invalida");
                }
            } else {
                System.out.println("A inscrição não esta pendente");
            }
        } else {
            System.out.println("Incompatibilidade de feira");
        }
    }

    public void rejeitarInscricao(Inscricao inscricao, GerenciadorDaFeira gerenciadorDaFeira) {

        if (inscricao.getFeira().getId() == gerenciadorDaFeira.getFeira().getId()) {

            if ("PENDENTE".equalsIgnoreCase(inscricao.getStatusAprovacao())) {
                inscricao.setStatusAprovacao("REJEITADA");
                inscricaoRepository.save(inscricao);
            } else {
                System.out.println("A inscrição não está pendente e não pode ser rejeitada");
            }

        } else {
            System.out.println("Imcompatilidade de feira");
        }
    }

    public boolean verificarInscricoesExitente(Feira feira, Feirante feirante) {

        List<Inscricao> inscricoes = inscricaoRepository.findAllByFeiraId(feira.getId());

        for (int indice = 0; indice < inscricoes.size(); indice++) {

            Inscricao inscricao = inscricoes.get(indice);

            if (feirante.getId() == inscricao.getFeirante().getId()
                && feira.getId() == inscricao.getFeira().getId()) {

                if ("PENDENTE".equalsIgnoreCase(inscricao.getStatusAprovacao())|| "APROVADA".equalsIgnoreCase(inscricao.getStatusAprovacao())) {
                    return true;
                }
            }
        }

        return false;
    }
}
