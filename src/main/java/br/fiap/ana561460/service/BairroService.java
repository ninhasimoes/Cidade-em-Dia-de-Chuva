package br.fiap.ana561460.service;

import br.fiap.ana561460.domain.Bairro;
import br.fiap.ana561460.dto.BairroDTO;
import br.fiap.ana561460.exception.NaoEncontradoException;
import br.fiap.ana561460.exception.RegraNegocioException;
import br.fiap.ana561460.mapper.BairroMapper;
import br.fiap.ana561460.repository.BairroRepository;
import br.fiap.ana561460.repository.TrechoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BairroService {
    private final BairroRepository repoBairro;
    private final BairroMapper mapper;
    private final TrechoRepository repoTrecho;


    public BairroService(BairroRepository repoBairro,
                         BairroMapper mapper, TrechoRepository repoTrecho) {
        this.repoTrecho = repoTrecho;
        this.mapper = mapper;
        this.repoBairro = repoBairro;
    }

    public List<BairroDTO> listar() {
        return repoBairro.findAllByOrderByNomeAsc().stream()
                .map(bairro -> mapper.toDTO(bairro))
                .toList();
    }

    public BairroDTO buscarPorId(Long id) {
        Bairro bairro = repoBairro.findById(id).orElseThrow(() -> new NaoEncontradoException("Bairro não localizado"));
        return mapper.toDTO(bairro);
    }

    public Bairro buscarEntidade(Long id) {
        return repoBairro.findById(id).orElseThrow(() ->
                new NaoEncontradoException("Bairro não localizado"));

    }

    @Transactional
    public BairroDTO salvar(BairroDTO dto) {
        Bairro bairro = mapper.toEntity(dto);
        return mapper.toDTO(repoBairro.save(bairro));
    }

    @Transactional
    public void excluir(Long id) {
        if (repoTrecho.existsByBairroId(id)) {
            throw new RegraNegocioException("Este bairro possui trechos cadastrados e não pode ser excluído");
        }

        repoBairro.deleteById(id);
    }
}
