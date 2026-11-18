package br.fiap.ana561460.repository;

import br.fiap.ana561460.domain.Leitura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeituraRepository extends JpaRepository<Leitura, Long> {
    List<Leitura> findAllByOrderByDataHoraDesc();
    Optional<Leitura> findFirstByTrechoIdOrderByDataHoraDesc(Long trechoId);
    boolean existsByTrechoId(Long trechoId);
    void deleteByTrechoId(Long trechoId);
}
