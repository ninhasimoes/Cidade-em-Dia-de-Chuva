package br.fiap.ana561460.repository;


import br.fiap.ana561460.domain.Trecho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrechoRepository extends JpaRepository<Trecho, Long> {
    List<Trecho> findAllByOrderByLogradouroAsc();
    List<Trecho>
    findByBairroIdOrderByLogradouroAsc(Long bairroId);
    boolean existsByBairroId(Long bairroId);
}
