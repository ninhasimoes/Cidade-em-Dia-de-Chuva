package br.fiap.ana561460.service;

import br.fiap.ana561460.domain.Leitura;
import br.fiap.ana561460.domain.Trecho;
import br.fiap.ana561460.dto.PainelItemDTO;
import br.fiap.ana561460.repository.LeituraRepository;
import br.fiap.ana561460.repository.TrechoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PainelService {
    private final TrechoRepository repoTrecho;
    private final LeituraRepository repoLeitura;

    public PainelService(TrechoRepository repoTrecho, LeituraRepository repoLeitura) {
        this.repoTrecho = repoTrecho;
        this.repoLeitura = repoLeitura;
    }

    public List<PainelItemDTO> montarPainel(Long bairroId){
        List<PainelItemDTO> paineis = new ArrayList<>();
        List<Trecho> trechos = repoTrecho.findByBairroIdOrderByLogradouroAsc(bairroId);
        for (Trecho trecho : trechos) {
            PainelItemDTO item = new PainelItemDTO();
            item.setLogradouro(trecho.getLogradouro());
            item.setTipoCalcada(trecho.getTipoCalcada());
            item.setAltitude(trecho.getAltitude());
            Optional<Leitura> leitura = repoLeitura.findFirstByTrechoIdOrderByDataHoraDesc(trecho.getId());
            if (leitura.isPresent()) {
                 item.setUltimaLamina(leitura.get().getLaminaCm());
                 item.setUltimoStatus(leitura.get().getStatus());
            }
            paineis.add(item);
        }
        return paineis;

    }
}
