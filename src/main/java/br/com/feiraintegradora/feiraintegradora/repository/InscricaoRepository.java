package br.com.feiraintegradora.feiraintegradora.repository;

import br.com.feiraintegradora.feiraintegradora.model.Inscricao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InscricaoRepository extends JpaRepository<Inscricao, Integer> {
    
    List<Inscricao> findAllByFeiraId(int feiraId);
}