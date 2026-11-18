package br.fiap.ana561460.service;

import br.fiap.ana561460.domain.Bairro;
import br.fiap.ana561460.domain.Trecho;
import br.fiap.ana561460.dto.TrechoDTO;
import br.fiap.ana561460.exception.NaoEncontradoException;
import br.fiap.ana561460.mapper.TrechoMapper;
import br.fiap.ana561460.repository.LeituraRepository;
import br.fiap.ana561460.repository.TrechoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrechoService {
    private final TrechoRepository repoTrecho;
    private final TrechoMapper mapper;
    private final LeituraRepository repoLeitura;
    private final BairroService bairroService;


    public TrechoService(TrechoRepository repoTrecho, TrechoMapper mapper,
                         LeituraRepository repoLeitura, BairroService bairroService) {
        this.repoTrecho = repoTrecho;
        this.mapper = mapper;
        this.repoLeitura = repoLeitura;
        this.bairroService = bairroService;
    }

    public List<TrechoDTO> listar() {
        return repoTrecho.findAllByOrderByLogradouroAsc().stream()
                .map(trecho -> mapper.toDTO(trecho))
                .toList();
    }

    public TrechoDTO buscarPorId(Long id) {
        Trecho trecho = repoTrecho.findById(id).orElseThrow(() -> new NaoEncontradoException("Trecho não localizado"));
        return mapper.toDTO(trecho);
    }

    public Trecho buscarEntidade(Long id) {
        return repoTrecho.findById(id).orElseThrow(() ->
                new NaoEncontradoException("Trecho não localizado"));

    }

    @Transactional
    public TrechoDTO salvar(TrechoDTO dto) {
        Bairro bairro = bairroService.buscarEntidade(dto.getBairroId());
        Trecho trecho = mapper.toEntity(dto,bairro);
        return mapper.toDTO(repoTrecho.save(trecho));
    }

    @Transactional
    public void excluir(Long id) {
        repoLeitura.deleteByTrechoId(id);
        repoTrecho.deleteById(id);
    }
}
