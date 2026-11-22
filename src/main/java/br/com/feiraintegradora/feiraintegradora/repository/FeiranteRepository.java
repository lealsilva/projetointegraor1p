
package br.com.feiraintegradora.feiraintegradora.repository;

import br.com.feiraintegradora.feiraintegradora.model.Feirante;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeiranteRepository extends JpaRepository<Feirante, Integer> {

    // Busca feirante pelo e-mail
    Optional<Feirante> findByEmail(String email);

    // Verifica se o e-mail já está cadastrado
    boolean existsByEmail(String email);

    // Verifica se o CPF ou CNPJ já está cadastrado
    boolean existsByCpfCnpj(String cpfCnpj);

}
