package br.fiap.ana561460.service;

import br.fiap.ana561460.domain.Leitura;
import br.fiap.ana561460.domain.Trecho;
import br.fiap.ana561460.dto.LeituraDTO;
import br.fiap.ana561460.exception.NaoEncontradoException;
import br.fiap.ana561460.mapper.LeituraMapper;
import br.fiap.ana561460.repository.LeituraRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeituraService {
    private final TrechoService trechoService;
    private final LeituraMapper mapper;
    private final LeituraRepository repoLeitura;
    private static final int LIMIAR = 12;


    public LeituraService(TrechoService trechoService, LeituraMapper mapper,
                         LeituraRepository repoLeitura) {
        this.trechoService = trechoService;
        this.mapper = mapper;
        this.repoLeitura = repoLeitura;
    }

    public List<LeituraDTO> listar() {
        return repoLeitura.findAllByOrderByDataHoraDesc().stream()
                .map(leitura -> mapper.toDTO(leitura))
                .toList();
    }

    public LeituraDTO buscarPorId(Long id) {
        Leitura leitura = repoLeitura.findById(id).orElseThrow(() -> new NaoEncontradoException("Leitura não localizada"));
        return mapper.toDTO(leitura);
    }

    public String calcularStatus(int laminaCm){
        String status;
        if (laminaCm<LIMIAR/2){
            status = "NORMAL";
        } else if (laminaCm<LIMIAR) {
            status = "ATENCAO";
        }else {
            status = "ALERTA";
    }
        return status;
    }

    @Transactional
    public LeituraDTO salvar(LeituraDTO dto) {
        Trecho trecho = trechoService.buscarEntidade(dto.getTrechoId());
        String status = calcularStatus(dto.getLaminaCm());
        Leitura leitura = mapper.toEntity(dto,trecho,status);
        return mapper.toDTO(repoLeitura.save(leitura));
    }

    @Transactional
    public void excluir(Long id) {
        repoLeitura.deleteById(id);
    }
}

