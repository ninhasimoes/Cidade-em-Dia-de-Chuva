package br.fiap.ana561460.repository;

import br.fiap.ana561460.domain.Bairro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BairroRepository extends JpaRepository<Bairro, Long> {
    List<Bairro> findAllByOrderByNomeAsc();
}
